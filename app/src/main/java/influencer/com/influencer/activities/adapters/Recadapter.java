package influencer.com.influencer.activities.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.activity.influencer.ActivityPost;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.searchCampaign.Campaign;

public class Recadapter extends RecyclerView.Adapter<Recadapter.ListViewHolder> {
private List<Campaign> imgList; ;
Context context;

    String searchText;

    public Recadapter(List<Campaign> imgList) {
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
        final Campaign recpojo = imgList.get(position);
        holder.imgtitle.setText(recpojo.getTitle());
        holder.imgcomment.setText(recpojo.getBrandname());
        if (recpojo.getBanner()==null)
        {
            Picasso.with(context).load(R.drawable.logo_influencer).into(holder.recimage);

        }
        else
        {
            Picasso.with(context).load("http://192.168.0.61"+recpojo.getBanner()).into(holder.recimage);

        }


        if (recpojo.getBrandlogo()==null)
        {
            Picasso.with(context).load(R.drawable.logo_influencer).into(holder.logo);
        }
        else
        {
            Picasso.with(context).load("http://192.168.0.61"+recpojo.getBrandlogo()).into(holder.logo);

        }



        holder.recimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ActivityPost.class);
                intent.putExtra("img","http://192.168.0.61"+recpojo.getBanner());
                intent.putExtra("title",recpojo.getTitle());
                intent.putExtra("detail",recpojo.getDetail());
                intent.putExtra("price",recpojo.getReward());
                intent.putExtra("logo","http://192.168.0.61"+recpojo.getBrandlogo());
                intent.putExtra("name",recpojo.getBrandname());
                intent.putExtra("ratio",recpojo.getGenderratio());
                intent.putExtra("agefrom",recpojo.getAgefrom());
                intent.putExtra("ageto",recpojo.getAgeto());
                intent.putExtra("view","list");
                intent.putExtra("imgid",recpojo.getId());
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
        CircleImageView logo;

        public ListViewHolder(View itemView) {
            super(itemView);
            recimage=itemView.findViewById(R.id.recimage);
            imgtitle=itemView.findViewById(R.id.imgtitle);
            imgcomment=itemView.findViewById(R.id.imgcomment);
            logo=itemView.findViewById(R.id.circleImageView3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });



        }


    }
}
