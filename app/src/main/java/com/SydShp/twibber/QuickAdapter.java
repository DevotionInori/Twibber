package com.SydShp.twibber;

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.SydShp.twibber.model.Twibber;
import com.SydShp.twibber.model.User;

import org.litepal.LitePal;

import java.io.Serializable;
import java.util.List;

// ① 创建Adapter
public abstract class QuickAdapter<T> extends RecyclerView.Adapter<QuickAdapter.VH> implements Serializable {

    private RecyclerView mRecyclerView;

    private List<T> mDatas;
    private Context mContext;

    public QuickAdapter(List<T> datas,Context mContext){
        this.mDatas = datas;
        this.mContext=mContext;
    }

    public abstract int getLayoutId(int viewType);


    private View VIEW_FOOTER;
    private View VIEW_HEADER;

    //Type
    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new VH(VIEW_FOOTER);
        } else if (viewType == TYPE_HEADER) {
            return new VH(VIEW_HEADER);
        } else {
            return VH.get(parent,getLayoutId(viewType));
        }
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (!isHeaderView(position) && !isFooterView(position)) {
            if (haveHeaderView()) position--;
            convert(holder, mDatas.get(position), position);
            final int p = position;


            holder.getView(R.id.icAvatar).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(mContext,HisOrHerHome.class);
                    Twibber tb = (Twibber) getItem(p);
                    in.putExtra("User",(Serializable) LitePal.find(User.class,tb.getPublisherID()));
                    mContext.startActivity(in);
                }
            });
            holder.getView(R.id.nameText).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(mContext,HisOrHerHome.class);
                    Twibber tb = (Twibber) getItem(p);
                    in.putExtra("User",(Serializable)LitePal.find(User.class,tb.getPublisherID()));
                    mContext.startActivity(in);
                }
            });
            holder.getView(R.id.twibberContent).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(mContext,TwibberPage.class);
                    in.putExtra("twibber",(Serializable)getItem(p));
                    mContext.startActivity(in);
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(mContext,TwibberPage.class);
                    in.putExtra("twibber",(Serializable)getItem(p));
                    mContext.startActivity(in);
                }
            });
        }
    }

    public void setmDatas(List<T> datas){
        this.mDatas=datas;
    }


    @Override
    public int getItemCount() {
        int count = (mDatas == null ? 0 : mDatas.size());
        if (VIEW_FOOTER != null) {
            count++;
        }

        if (VIEW_HEADER != null) {
            count++;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View getLayout(int layoutId) {
        return LayoutInflater.from(mContext).inflate(layoutId, null);
    }

    public void addHeaderView(View headerView) {
        if (haveHeaderView()) {
            throw new IllegalStateException("hearview has already exists!");
        } else {
            //避免出现宽度自适应
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            headerView.setLayoutParams(params);
            VIEW_HEADER = headerView;
            notifyItemInserted(0);
        }

    }

    public void addFooterView(View footerView) {
        if (haveFooterView()) {
            throw new IllegalStateException("footerView has already exists!");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            VIEW_FOOTER = footerView;
            notifyItemInserted(getItemCount() - 1);
        }
    }

    public abstract void convert(VH holder, T data, int position);

    private boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }

    public boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

    private boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }

    private boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
    }

    public T getItem(int position){
        return mDatas.get(position);
    }

    static class VH extends RecyclerView.ViewHolder{
        private SparseArray<View> mViews;
        private View mConvertView;

        private VH(View v){
            super(v);
            mConvertView = v;
            mViews = new SparseArray<>();
        }


        public static VH get(ViewGroup parent, int layoutId){
            View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new VH(convertView);
        }

        public <T extends View> T getView(int id){
            View v = mViews.get(id);
            if(v == null){
                v = mConvertView.findViewById(id);
                mViews.put(id, v);
            }
            return (T)v;
        }

        public void setText(int id, String value){
            TextView view = getView(id);
            view.setText(value);
        }

    }
}