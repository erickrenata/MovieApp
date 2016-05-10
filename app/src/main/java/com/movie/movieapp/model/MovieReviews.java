package com.movie.movieapp.model;

import java.util.List;

/**
 * Created by asus on 09/05/2016.
 */
public class MovieReviews {
    /**
     * id : 99861
     * page : 1
     * results : [{"id":"56351986c3a3681b59017d48","author":"Hotsake","content":"There is a lot going on in this movie but it all just seemed kind of pointless and dull to me. Every now and then there would be a good scene or piece of dialogue but for the most part I felt myself getting bored and reaching for my phone to play Cribbage. I guess what I didn't like most is that it all felt like a big filler episode instead of an action packed character driven story arc.","url":"https://www.themoviedb.org/review/56351986c3a3681b59017d48"}]
     * total_pages : 1
     * total_results : 1
     */

    private int id;
    private int page;
    private int total_pages;
    private int total_results;
    /**
     * id : 56351986c3a3681b59017d48
     * author : Hotsake
     * content : There is a lot going on in this movie but it all just seemed kind of pointless and dull to me. Every now and then there would be a good scene or piece of dialogue but for the most part I felt myself getting bored and reaching for my phone to play Cribbage. I guess what I didn't like most is that it all felt like a big filler episode instead of an action packed character driven story arc.
     * url : https://www.themoviedb.org/review/56351986c3a3681b59017d48
     */

    private List<ResultsBean> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String id;
        private String author;
        private String content;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
