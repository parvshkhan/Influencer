package influencer.com.influencer.activities.recAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import influencer.com.influencer.R;
import influencer.com.influencer.activities.pojoclass.Recpojo;

public class Recadapter extends RecyclerView.Adapter<Recadapter.ListViewHolder> {
private List<Recpojo> imgList; ;



    public Recadapter(List<Recpojo> imgList) {
        this.imgList = imgList;

    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_row, parent, false);

        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Recpojo recpojo = imgList.get(position);
        holder.imgtitle.setText(recpojo.getImgtitle());
        holder.imgcomment.setText(recpojo.getImgcomment());
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        
        EditText imgcomment,imgtitle;
        ImageView recimage;

        public ListViewHolder(View itemView) {
            super(itemView);
            recimage=itemView.findViewById(R.id.recimage);
            imgtitle=itemView.findViewById(R.id.imgtitle);
            imgcomment=itemView.findViewById(R.id.imgcomment);
            
        }
    }
}
