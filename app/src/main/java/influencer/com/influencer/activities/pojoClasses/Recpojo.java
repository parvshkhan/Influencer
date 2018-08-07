package influencer.com.influencer.activities.pojoClasses;

public class Recpojo {

   private String imgtitle;
   private String imgcomment;
   private String recimgage;


    public Recpojo(String imgtitle, String imgcomment,String recimgage) {
        this.imgtitle = imgtitle;
        this.imgcomment = imgcomment;
        this.recimgage=recimgage;
    }

    public String getRecmgage() {
        return recimgage;
    }

    public String getImgtitle() {
        return imgtitle;

    }

    public String getImgcomment() {
        return imgcomment;
    }
}
