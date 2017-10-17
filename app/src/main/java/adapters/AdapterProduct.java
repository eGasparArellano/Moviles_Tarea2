package adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iteso.desarrollo.sesion9_2.ActivityDetail;
import com.iteso.desarrollo.sesion9_2.ActivityMain;
import com.iteso.desarrollo.sesion9_2.R;

import java.util.ArrayList;

import beans.ItemProduct;
import static Commons.Constants.*;

/**
 * Created by Desarrollo on 24/09/2017.
 */

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {
    private ArrayList<ItemProduct> mDataSet;
    private Context context;


    public AdapterProduct(Context context, ArrayList<ItemProduct> myDataSet){
        mDataSet = myDataSet;
        this.context = context;
    }

    @Override
    // Crea nuevas vistas (es invocado por el Layout Manager)
    public AdapterProduct.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    // Reemplaza los contenidos de la vista (es invocado por el Layout Manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mProductTitle.setText(mDataSet.get(position).getTitle());
        holder.mProductStore.setText(mDataSet.get(position).getStore().getName());
        holder.mProductLocation.setText(mDataSet.get(position).getStore().getCity().getName() + ", Jalisco");
        holder.mProductPhone.setText(mDataSet.get(position).getStore().getPhone());

        switch (mDataSet.get(position).getImage()){
            case 0:
                holder.mProductImage.setImageResource(R.drawable.mac);
                break;
            case 1:
                holder.mProductImage.setImageResource(R.drawable.alienware);
                break;
        }

        Bitmap bitmap =  ((BitmapDrawable) holder.mProductThumbnail.getDrawable()).getBitmap();
        holder.mProductThumbnail.setImageBitmap(bitmap);

        holder.mDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, mDataSet.get(position).toString(), Toast.LENGTH_LONG).show();
            }
        });

        holder.mProductPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mDataSet.get(position).getStore().getPhone()));
                context.startActivity(intent);
            }
        });

        holder.mEventLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityDetail.class);
                intent.putExtra("ITEM", mDataSet.get(position));
                ((ActivityMain)context).startActivityForResult(intent, DETAIL_SUBACTIVITY);
            }
        });
    }

    @Override
    // Regresa el tamaño de tu dataset (es invocado por el Layout Manager)
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public Button mDetail;
        public TextView mProductTitle;
        public TextView mProductStore;
        public TextView mProductLocation;
        public TextView mProductPhone;
        public ImageView mProductImage;
        public ImageView mProductThumbnail;
        public RelativeLayout mEventLayout;

        public ViewHolder(View v) {
            super(v);
            mEventLayout = (RelativeLayout) v.findViewById(R.id.item_product_layout);
            mDetail = (Button) v.findViewById(R.id.item_product_detail);
            mProductTitle = (TextView) v.findViewById(R.id.item_product_title);
            mProductStore = (TextView) v.findViewById(R.id.item_product_store);
            mProductLocation = (TextView) v.findViewById(R.id.item_product_location);
            mProductPhone = (TextView) v.findViewById(R.id.item_product_phone);
            mProductImage = (ImageView) v.findViewById(R.id.item_product_image);
            mProductThumbnail = (ImageView) v.findViewById(R.id.item_product_thumbnail);
        }
    }
}
