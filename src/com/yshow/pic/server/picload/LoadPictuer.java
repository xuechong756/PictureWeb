package com.yshow.pic.server.picload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.yshow.pic.contant.PicContant;
import com.yshow.pic.dao.db.CollectionSave;
import com.yshow.pic.dao.db.T_baidulocalMapper;
import com.yshow.pic.model.db.pic.T_baidulocal;
import com.yshow.pic.model.db.pic.T_phoalb;
import com.yshow.pic.model.db.pic.T_phoatlas;
import com.yshow.pic.model.net.baidu.Contentlist;
import com.yshow.pic.model.net.baidu.Pagebean;
import com.yshow.pic.model.net.baidu.RequstResult;
import com.yshow.pic.model.net.baidu.ResultPic;
import com.yshow.pic.server.buftable.TableBuffer;
import com.yshow.pic.server.session.SqlSessionFact;
import com.yshow.pic.utils.HttpConnectManager;
import com.yshow.pic.utils.StaticTools;
import com.yshow.pic.utils.UrlCus;

public class LoadPictuer {

	private static final Logger LOG = LoggerFactory.getLogger(LoadPictuer.class);
	private HttpConnectManager httpConnectManager;

	public LoadPictuer() {
		httpConnectManager = HttpConnectManager.getInstance();
	}

	public void requestPic() {
		T_baidulocal[] t_phtypes = TableBuffer.getT_baidulocal();
		for(T_baidulocal t_phtype : t_phtypes)
		{
			httpConnectManager.HttpGetConnect(PicContant.getPicURL(t_phtype.getBlBaiduid(), 1), 
					PicContant.requestHeader, null, callback);
		}
	}

	private Callback callback = new Callback() {
		@Override
		public void onFailure(Request arg0, IOException arg1) {
			System.out.println(arg0.urlString());
		}
		@Override
		public void onResponse(Response arg0) throws IOException {
			saveDb(arg0);
		}
	};

	private void saveDb(Response response) {
		try {
			String body = response.body().string();
			RequstResult requstResult = StaticTools.getGson().fromJson(body, RequstResult.class);
			loadNextPage(requstResult.getShowapi_res_body().getPagebean(),
					response.request().urlString());
			final List<Contentlist> list = requstResult.getShowapi_res_body().getPagebean().getContentlist();
			Map<String, ArrayList<String>> map = UrlCus.getQuery(response.request().urlString());
			final String type = map.get("type").get(0);
			if(type == null || !StaticTools.isPosiInt(type))
				return;
			if (list != null && !list.isEmpty()) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						SqlSession sqlSession = null;
						try{
							Integer id = Integer.parseInt(type);
							sqlSession = SqlSessionFact.getSqlSession();
							T_baidulocalMapper t_baidulocalMapper = sqlSession.getMapper(T_baidulocalMapper.class);
							id = t_baidulocalMapper.selectTypeBybaiduid(id);
							if(id == null)//没有此种类型，则抛弃
							{
								if(sqlSession!=null)
									sqlSession.close();
								return;
							}
							List<T_phoatlas> tpList = new ArrayList<>();
							for(Contentlist contentlist : list)
							{
								T_phoatlas t_phoatlas = new T_phoatlas();
								t_phoatlas.setPcoTitle(contentlist.getTitle());
								t_phoatlas.setPcoHptpid(id);
								List<T_phoalb> tphoalbList = t_phoatlas.getT_phoalbList();
								List<ResultPic> resuList = contentlist.getList();
								for(ResultPic resultPic : resuList)
								{
									T_phoalb t_phoalb = new T_phoalb();
									t_phoalb.setPalBigurl(resultPic.getBig());
									t_phoalb.setPalMidurl(resultPic.getMiddle());
									t_phoalb.setPalSmlurl(resultPic.getSmall());
									tphoalbList.add(t_phoalb);
								}
								tpList.add(t_phoatlas);
							}
							//写入数据库
							new CollectionSave().phoatlas(tpList);
						}catch(Exception e)
						{
							LOG.error("", e);
						}
						finally{
							if(sqlSession!=null)
								sqlSession.close();
						}
					}
				}).start();
			}
		} catch (Exception e) {
			LOG.error("百度图片返回数据为空", e);
		}
	}
	
	//加载下一页，直到没有
	private void loadNextPage(Pagebean pagebean, String url)
	{
		String pageAll = pagebean.getAllPages();
		String currentPage = pagebean.getCurrentPage();
		if(StaticTools.isPosiInt(pageAll)&&StaticTools.isPosiInt(currentPage))
		{
			int current = Integer.parseInt(currentPage);
			if(Integer.parseInt(pageAll)> current)
			{
				Map<String, ArrayList<String>> map = UrlCus.getQuery(url);
				if(map.get("type")!=null)
				{
					String type = map.get("type").get(0);
					if(StaticTools.isPosiInt(type))
					{
						httpConnectManager.HttpGetConnect(
								PicContant.getPicURL(Integer.parseInt(type), ++current), 
								PicContant.requestHeader, null, callback);
					}
				}
			}
		}
	}
}
