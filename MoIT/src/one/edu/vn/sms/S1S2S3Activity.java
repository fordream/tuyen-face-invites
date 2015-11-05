package one.edu.vn.sms;

import com.facebook.FacebookException;
import com.facebook.avnp.Facebook415Utils;
import com.facebook.avnp.Facebook415Utils.IFacebookListener;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class S1S2S3Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Facebook415Utils.getInstance().onCreate(this, savedInstanceState, createIFacebookListener());
		setContentView(R.layout.invite);
		findViewById(R.id.invite).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Facebook415Utils.getInstance().invite();
			}
		});

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Facebook415Utils.getInstance().onSaveInstanceState(outState);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Facebook415Utils.getInstance().onResume();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Facebook415Utils.getInstance().onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
		super.onPause();
		Facebook415Utils.getInstance().onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Facebook415Utils.getInstance().onDestroy();
	}

	public IFacebookListener createIFacebookListener() {
		return new IFacebookListener() {

			@Override
			public void inviteSuccess(com.facebook.share.widget.AppInviteDialog.Result result) {

				Builder builder = new Builder(S1S2S3Activity.this);
				builder.setMessage("Add o day");
				builder.show();
			}

			@Override
			public void inviteOnError(FacebookException error) {

			}

			@Override
			public void inviteOnCancel() {

			}
		};
	}
}