package com.liuhongchen.bsitemprovider.service.impl;

import com.liuhongchen.bscommonmodule.pojo.Book;
import com.liuhongchen.bscommonutils.common.LogUtils;
import com.liuhongchen.bsitemprovider.service.EsBookService;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
@Service
public class EsBookServiceImpl implements EsBookService {

    @Autowired
    private JestClient jestClient;

    @Autowired
    private LogUtils logUtils;

    @Override
    public void saveBook(Book book) {

        Index index = new Index.Builder(book).index("book").id(book.getId()).type("book_type").build();
        try {
            DocumentResult execute = jestClient.execute(index);
            System.out.println(execute);
        } catch (IOException e) {
            e.printStackTrace();
            logUtils.i("item_provider_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());

        }
    }

    @Override
    public void saveBook(List<Book> bookList) {
        Bulk.Builder bulk = new Bulk.Builder();

        for(Book book : bookList) {
            Index index = new Index.Builder(book).index("book").type("book_type").id(book.getId()).build();
            bulk.addAction(index);
        }
        try {
            jestClient.execute(bulk.build());
        } catch (IOException e) {
            e.printStackTrace();
            logUtils.i("item_provider_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
        }
    }

    @Override
    public List<Book> searchBookByTitle(String title) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

//searchSourceBuilder.query(QueryBuilders.queryStringQuery(searchContent));

//searchSourceBuilder.field("name");
        searchSourceBuilder.query(QueryBuilders.matchQuery("title",title));
//                .query(QueryBuilders.matchQuery("keyword",title))
//                .query(QueryBuilders.matchQuery("author",title));

        Search search = new Search.Builder(searchSourceBuilder.toString())

                .addIndex("book").addType("book_type").build();

        try {

            JestResult result = jestClient.execute(search);

            return result.getSourceAsObjectList(Book.class);

        } catch (IOException e) {
            e.printStackTrace();
            logUtils.i("item_provider_exception"
                    , DateFormat.getDateInstance().format(new Date())+"--"
                            +Thread.currentThread() .getStackTrace()[1].getMethodName()
                            +e.getMessage());
        }

        return null;
    }
}
