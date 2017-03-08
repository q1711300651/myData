package TestNosql;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ww on 17/2/17.
 */
//@Log4j
public class TestElasticSearch {
//    链接：https://zhuanlan.zhihu.com/p/24011068

   public static void main(String[] args) throws UnknownHostException {

        @SuppressWarnings("resource")
        //如果cluster_name 不是elasticSearch
//        Settings settings=Settings.builder()
//                .put("cluster.name","elasticsearch_ww")
//                .put("node.name","VtyB9TR")
//                .build();
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(
                        InetAddress.getByName("127.0.0.1"), 9300));
       //如果是集群 这里还可以添加一个
       //.addtTransportAddresss(new InetSocketTransportAddress());

        Map<String, Object> json = new HashMap<String, Object>();

        json.put("user", "jack k");

        json.put("postDate", "2016-11-23");

        json.put("message", "es demo");

        client.prepareIndex("twitter", "tweet", "1").setType("muti_field")

                .setSource(json).execute().actionGet();

        GetResponse response = client.prepareGet("twitter", "tweet", "1")

                .execute().actionGet();

        Map<String, Object> map = response.getSource();

        Iterator<Map.Entry<String, Object>> iter = map.entrySet().iterator();

        while (iter.hasNext()) {

            Map.Entry<String, Object> ent = iter.next();

            System.out.println(ent.getKey() + "=" + ent.getValue());

        }

        QueryBuilder qb = QueryBuilders.matchQuery("user", "jack");

        SearchResponse scrollResp = client.prepareSearch("twitter")

                .setSearchType(SearchType.DEFAULT)

                .setScroll(new TimeValue(60000)).setQuery(qb).setSize(100)

                .execute().actionGet();

        System.out.println("通过user字段查询:" + scrollResp);

        System.out.println(

                "通过user字段查询信息数量:" + scrollResp.getHits().getTotalHits());

        for (SearchHit hit : scrollResp.getHits()) {

            Iterator<Map.Entry<String, Object>> rpItor = hit.getSource().entrySet()

                    .iterator();

            while (rpItor.hasNext()) {

                Map.Entry<String, Object> rpEnt = rpItor.next();

                System.out.println(rpEnt.getKey() + " : " + rpEnt.getValue());

            }

        }

        client.close();

    }
}
