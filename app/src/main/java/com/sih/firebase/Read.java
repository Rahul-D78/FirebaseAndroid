package com.sih.firebase;

public class Read {

    String title;
    String subTitle;


        public Read() {

        }

        public Read(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle() {
            this.title = title;
        }

        public String getSubTitle(){
            return subTitle;
        }

        public void setSubTitle() {
            this.subTitle = subTitle;
        }
    }
