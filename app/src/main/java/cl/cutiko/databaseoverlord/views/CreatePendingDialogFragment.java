package cl.cutiko.databaseoverlord.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.List;

import cl.cutiko.databaseoverlord.R;
import cl.cutiko.databaseoverlord.database.PendingCreater;
import cl.cutiko.databaseoverlord.models.Pending;

/**
 * Created by cutiko on 10-03-17.
 */

public class CreatePendingDialogFragment extends DialogFragment {

    private CreateCallback callback;

    public static CreatePendingDialogFragment newInstance() {
        return new CreatePendingDialogFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (CreateCallback) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialogfragment_createpending, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final EditText editText = (EditText) view.findViewById(R.id.pendingEt);
        ImageButton button = (ImageButton) view.findViewById(R.id.saveBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pending pending = new Pending(editText.getText().toString(), false);
                new CreatePending().execute(pending);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
    }

    private class CreatePending extends PendingCreater {

        public CreatePending() {
            super(getContext());
        }

        @Override
        protected void onPostExecute(List<Pending> pendings) {
            if (pendings != null && pendings.size() > 0) {
                callback.created(pendings.get(0));
            }
            dismiss();
        }
    }


}
