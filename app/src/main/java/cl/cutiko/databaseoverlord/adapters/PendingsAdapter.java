package cl.cutiko.databaseoverlord.adapters;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cl.cutiko.databaseoverlord.R;
import cl.cutiko.databaseoverlord.database.PendingUpdater;
import cl.cutiko.databaseoverlord.models.Pending;

/**
 * Created by cutiko on 27-10-16.
 */

public class PendingsAdapter extends RecyclerView.Adapter<PendingsAdapter.ViewHolder> {

    private List<Pending> pendings = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pending, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Pending pending = pendings.get(position);

        CheckBox checkBox = holder.status;
        checkBox.setChecked(pending.getStatus());
        holder.name.setText(pending.getName());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Pending auxPending = pendings.get(holder.getAdapterPosition());
                            new PendingUpdater(compoundButton.getContext()).execute(auxPending.getId());
                            pendings.remove(holder.getAdapterPosition());
                            notifyItemRemoved(holder.getAdapterPosition());
                        }
                    }, 100);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pendings.size();
    }

    public void addPendings(List<Pending> pendings) {
        if (pendings != null) {
            this.pendings.clear();
            this.pendings.addAll(pendings);
            notifyDataSetChanged();
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        CheckBox status;

        ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.pendingTv);
            status = (CheckBox) view.findViewById(R.id.pendingCb);
        }

    }

}
