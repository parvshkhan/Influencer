package influencer.com.influencer.activities.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import influencer.com.influencer.R;
import influencer.com.influencer.activities.activity.influencer.ActivityPost;
import influencer.com.influencer.activities.model.Recpojo;

public class Recadapter extends RecyclerView.Adapter<Recadapter.ListViewHolder> {
private List<Recpojo> imgList; ;
Context context;

    public Recadapter(List<Recpojo> imgList) {
        this.imgList = imgList;

    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mainlist, parent, false);
context=parent.getContext();


        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Recpojo recpojo = imgList.get(position);
        holder.imgtitle.setText(recpojo.getImgtitle());
        holder.imgcomment.setText(recpojo.getImgcomment());
        holder.recimage.setImageResource(R.drawable.flower);

        holder.recimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ActivityPost.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        
        TextView imgcomment,imgtitle;
        ImageView recimage;

        public ListViewHolder(View itemView) {
            super(itemView);
            recimage=itemView.findViewById(R.id.recimage);
            imgtitle=itemView.findViewById(R.id.imgtitle);
            imgcomment=itemView.findViewById(R.id.imgcomment);
            
        }
    }
}
