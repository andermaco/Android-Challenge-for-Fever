package com.andermaco.test.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andermaco.test.R;
import com.andermaco.test.model.Crew;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

import static com.andermaco.test.model.realm.InitialDataRealmTransaction.AREAS;
import static com.andermaco.test.model.realm.InitialDataRealmTransaction.RACE;


public class CrewListAdapter extends RecyclerView.Adapter<CrewListAdapter.ViewHolder> implements RealmChangeListener {

    private RealmResults<Crew> mCrew;
    private OnCrewClickListener mOnCrewClickListener;
    private Context mContext;

    public CrewListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crew, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Crew crew = mCrew.get(position);
        // Set position colour
        if (crew.getArea().getName()
                .equalsIgnoreCase(AREAS.ENGINEERING.name())) {
                holder.mPosition.setBackgroundColor(ContextCompat.getColor(mContext, R.color.red));
        } else if (crew.getArea().getName()
                .equalsIgnoreCase(AREAS.COMMAND.name())) {
                holder.mPosition.setBackgroundColor(ContextCompat.getColor(mContext, R.color.yellow));
        } else if (crew.getArea().getName()
                .equalsIgnoreCase(AREAS.SCIENCE.name())) {
            holder.mPosition.setBackgroundColor(ContextCompat.getColor(mContext, R.color.blue));
        } else {
            holder.mPosition.setBackgroundColor(ContextCompat.getColor(mContext, R.color.other));

        }
        // Set crew name
        holder.mTextCrewName.setText(crew.getName());
        // Set crew race
        holder.mTextCrewRace.setText(crew.getRace().getName());
        // Set multiplier
        setMultiplier(crew, holder);

        holder.mLayoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (mOnCrewClickListener != null) {
                    mOnCrewClickListener.onCrewClick();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCrew.size();
    }

    public void setOnCrewClickListener(final OnCrewClickListener onCrewClickListener) {
        mOnCrewClickListener = onCrewClickListener;
    }

    public void setCrew(final RealmResults<Crew> crew) {
        mCrew = crew;
        mCrew.addChangeListener(this);
        notifyDataSetChanged();
    }

    @Override
    public void onChange(Object o) {
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layout_item_container)
        LinearLayout mLayoutItem;
        @BindView(R.id.position)
        TextView mPosition;
        @BindView(R.id.multiplier)
        TextView mMultiplier;
        @BindView(R.id.crew_name)
        TextView mTextCrewName;
        @BindView(R.id.crew_race)
        TextView mTextCrewRace;
        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnCrewClickListener {
        void onCrewClick();
    }

    private void setMultiplier(Crew crew, ViewHolder holder) {
        if (crew.getRace().getName().equals(RACE.HUMAN.name()) && crew.getArea().getName().equals(AREAS.COMMAND.name())) {
            holder.mMultiplier.setText("x3");
        } else if (crew.getRace().getName().equals(RACE.HUMAN.name()) && crew.getArea().getName().equals(AREAS.ENGINEERING.name())) {
            holder.mMultiplier.setText("x2");
        } else if (crew.getRace().getName().equals(RACE.HUMAN.name()) && crew.getArea().getName().equals(AREAS.SCIENCE.name())) {
            holder.mMultiplier.setText("x1");
        } else if (crew.getRace().getName().equals(RACE.VULCAN.name()) && crew.getArea().getName().equals(AREAS.COMMAND.name())) {
            holder.mMultiplier.setText("x1");
        } else if (crew.getRace().getName().equals(RACE.VULCAN.name()) && crew.getArea().getName().equals(AREAS.ENGINEERING.name())) {
            holder.mMultiplier.setText("x2");
        } else if (crew.getRace().getName().equals(RACE.VULCAN.name()) && crew.getArea().getName().equals(AREAS.SCIENCE.name())) {
            holder.mMultiplier.setText("x3");
        } else if (crew.getRace().getName().equals(RACE.BETAZOID.name()) && crew.getArea().getName().equals(AREAS.COMMAND.name())) {
            holder.mMultiplier.setText("x2");
        } else if (crew.getRace().getName().equals(RACE.BETAZOID.name()) && crew.getArea().getName().equals(AREAS.ENGINEERING.name())) {
            holder.mMultiplier.setText("x3");
        } else if (crew.getRace().getName().equals(RACE.BETAZOID.name()) && crew.getArea().getName().equals(AREAS.SCIENCE.name())) {
            holder.mMultiplier.setText("x1");
        }

    }
}
