package com.apurva.assignment.thSensorDriver;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;


public class MyActivity extends Activity implements IReportBack  {
    private int mRemainingRunCount;
    private int mTotalRunCount;
    private MyAsyncTask mTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        mTask = null;
        mTotalRunCount = mRemainingRunCount = 0;
    }

    public void generateTH(View v) {
        TextView numRunInputView = (EditText)findViewById(R.id.counter_value);
        String input = numRunInputView.getText().toString();

        int inputVal = 0;
        if (!input.isEmpty()) {
            inputVal = Integer.parseInt(input);
        } else {
            updateAsyncTaskOutputView("Invalid value entered!\n", true);
        }

        if (inputVal != 0) {
            disableGenerateButton();
            mTotalRunCount = inputVal;
            mRemainingRunCount = mTotalRunCount;

            updateAsyncTaskOutputView("", true);
            launchAsync();
        }
    }

    private void launchAsync() {
        if(mRemainingRunCount > 0) {
            mRemainingRunCount--;
            mTask = new MyAsyncTask(this);
            mTask.execute();
        } else {
            mTask = null;
            mTotalRunCount = mRemainingRunCount = 0;
            enableGenerateButton();
        }
    }

    public void finishMainActivity(View v) {
        if(mTask != null)
            mTask.cancel(true);
        mTotalRunCount = mRemainingRunCount = 0;
        MyActivity.this.finish();
    }

    public void cancelAsyncTask(View v) {
        boolean forceClean = false;
        String message;
        if (mTask != null) {
            mTask.cancel(true);
            mTask = null;
            mTotalRunCount = mRemainingRunCount = 0;
            message = "Data Generation cancelled by user.\n";
        } else {
            forceClean = true;
            message = "No active data generation task to cancel.\n";
        }
        updateAsyncTaskOutputView(message, forceClean);

        enableGenerateButton();
    }

    @Override
    public void reportBack(String temperature, String humidity, String threadId) {
        TextView temperatureView, humidityView, activityThreadIdView;
        temperatureView = (TextView) findViewById(R.id.temp_value);
        humidityView = (TextView) MyActivity.this.findViewById(R.id.humidity_value);
        activityThreadIdView = (TextView) MyActivity.this.findViewById(R.id.activity_value);

        String temperatureText = temperature + "F";
        temperatureView.setText(temperatureText);
        String humidityText = humidity + "%";
        humidityView.setText(humidityText);
        activityThreadIdView.setText(threadId);

        StringBuilder message = new StringBuilder();
        message.append("Output ");
        message.append(Integer.toString(mTotalRunCount - mRemainingRunCount));
        message.append("\nTemperature : ");
        message.append(temperatureText);
        message.append("\nHumidity : ");
        message.append(humidityText);
        message.append("\nActivityId : ");
        message.append(threadId);
        message.append("\n");

        updateAsyncTaskOutputView(message.toString());

        // launch the next async data generation task
        launchAsync();
    }


    private void updateAsyncTaskOutputView(String message) {
        updateAsyncTaskOutputView(message, false);
    }

    private void updateAsyncTaskOutputView(String message, boolean forceClean) {
        TextView asyncTaskOutputView = (TextView) MyActivity.this.findViewById(R.id.async_task_view);
        StringBuilder text = new StringBuilder();
        String previousText = asyncTaskOutputView.getText().toString();
        if(!forceClean)
            text.append(previousText);
        text.append(message);
        asyncTaskOutputView.setText(text.toString());
    }

    private void enableGenerateButton() {
        Button generateButton = (Button) findViewById(R.id.btn_generate);
        generateButton.setEnabled(true);
    }

    private void disableGenerateButton() {
        Button generateButton = (Button) findViewById(R.id.btn_generate);
        generateButton.setEnabled(false);
    }
}
