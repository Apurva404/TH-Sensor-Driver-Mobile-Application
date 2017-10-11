package com.apurva.assignment.thSensorDriver;

import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<String, Void, Boolean>{
    private int mTemp;
    private int mHumidity;
    private long mThreadId;
    private IReportBack mTarget;

    public MyAsyncTask(IReportBack target) {
        mTemp = 0;
        mHumidity = 0;
        mTarget = target;

    }

    @Override
    protected Boolean doInBackground(String... params) {
        mThreadId = Utils.getThreadId();
        mTemp = Utils.generateRandomTemperature();
        mHumidity = Utils.generateRandomHumidity();

        Utils.sleepForSeconds(1);
        return true;
    }

    @Override
    protected void onPostExecute(Boolean val) {
        String t = new Integer(mTemp).toString();
        String h = new Integer(mHumidity).toString();
        String tid = new Long(mThreadId).toString();

        mTarget.reportBack(t, h, tid);
    }

}
