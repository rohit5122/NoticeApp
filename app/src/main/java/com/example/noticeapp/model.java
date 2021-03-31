package com.example.noticeapp;

public class model {



    String Title;
    String Discription;
    String Date;

    public static void get() {
    }

    public String getFile_Path() {
        return File_Path;
    }

    public void setFile_Path(String file_Path) {
        File_Path = file_Path;
    }

    String File_Path;

    private model() {}

    private model(String Title, String Discription, String Date, String File_Path){

        this.Title = Title;
        this.Discription = Discription;
        this.Date = Date;
        this.File_Path = File_Path;

    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }


}
