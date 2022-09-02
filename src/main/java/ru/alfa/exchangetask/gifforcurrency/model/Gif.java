package ru.alfa.exchangetask.gifforcurrency.model;


import lombok.Data;

@Data
public class Gif {

    private Data data;

    @lombok.Data
    public class Data {

        private String id;
        private String title;
        private String rating;
        private String embed_url;
        private String url;
        private Images images;

        @lombok.Data
        class Images {

            private Fixed_height_downsampled fixed_height_downsampled;

            @lombok.Data
            class Fixed_height_downsampled{

                private String url;
            }
        }
    }
}









