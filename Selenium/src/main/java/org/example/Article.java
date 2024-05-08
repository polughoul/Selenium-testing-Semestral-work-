package org.example;

public class Article {
    private String title;

    private String publicationDate;
    private  String doi;

    public Article(String title, String publicationDate, String doi) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.doi = doi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                '}';
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }
}
