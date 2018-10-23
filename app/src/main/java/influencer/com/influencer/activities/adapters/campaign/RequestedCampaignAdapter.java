package influencer.com.influencer.activities.adapters.campaign;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import influencer.com.influencer.R;
import influencer.com.influencer.activities.apiResponses.registerAPI.influencerAPI.campaign.requestedcampaign.RequestedCampaign;

public class RequestedCampaignAdapter extends RecyclerView.Adapter<RequestedCampaignAdapter.AtiveViewHolder> {

    private List<RequestedCampaign> imgList; ;
    Context context;

    public RequestedCampaignAdapter(List<RequestedCampaign> imgList) {
        this.imgList = imgList;

    }

    @NonNull
    @Override
    public AtiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_mainlist, parent, false);
        context=parent.getContext();


        return new AtiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AtiveViewHolder holder, int position) {

        RequestedCampaign recpojo = imgList.get(position);
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

            }
        });

    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public class AtiveViewHolder extends RecyclerView.ViewHolder
    {

        TextView imgcomment,imgtitle;
        ImageView recimage;
        CircleImageView logo;
        public AtiveViewHolder(View itemView) {
            super(itemView);

            recimage=itemView.findViewById(R.id.recimage);
            imgtitle=itemView.findViewById(R.id.imgtitle);
            imgcomment=itemView.findViewById(R.id.imgcomment);
            logo=itemView.findViewById(R.id.circleImageView3);
        }
    }
}
