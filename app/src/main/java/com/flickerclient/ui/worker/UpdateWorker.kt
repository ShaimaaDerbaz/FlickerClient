package com.flickerclient.ui.worker

import android.content.Context
import android.util.Log
import androidx.work.*
import com.flickerclient.data.ServiceLocator
import com.flickerclient.presenter.UpdateWorkerPresenter
import com.flickerclient.presenter.UpdateWorkerPresenterImp
import java.lang.Exception
import java.util.concurrent.TimeUnit

class UpdateWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    /*private val MINIMUM_REPEAT_INTERVAL = 15 * 60
    private val uniqueWorkName = "com.flickerclient.ui.worker.update_worker"
    private var repeatIntervalSec = MINIMUM_REPEAT_INTERVAL.toLong()*/
    private val flexIntervalSec = 5 * 60.toLong()

    var context: Context? = null

    var apiKey = "a3609e9f5da4b3adf065c943856621fb"
    var format = "json"
    var pageNum = 1
    var photosPerPage = 5


    override fun doWork(): Result {
        Log.d("doWork", Thread.currentThread().name)
        val mPresenter: UpdateWorkerPresenter =
            UpdateWorkerPresenterImp(ServiceLocator.instance!!.geFlickerImageRepository())
        try {
            mPresenter.getPopularImages(apiKey, photosPerPage, pageNum, format, 1).blockingFirst()

        }catch(exp :Exception)
        {
             return Result.failure()
        }
        return Result.success()
    }

    companion object {
        private val MINIMUM_REPEAT_INTERVAL = 15 * 60
        private val uniqueWorkName = "com.flickerclient.ui.worker.update_worker"
        private var repeatIntervalSec = MINIMUM_REPEAT_INTERVAL.toLong()
        fun enqueueSelf(context: Context) {
            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(
                    uniqueWorkName,
                    ExistingPeriodicWorkPolicy.KEEP,
                    getOwnWorkRequest()
                )
        }

        private fun getOwnWorkRequest(): PeriodicWorkRequest {
            val constraints: Constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
            return PeriodicWorkRequest.Builder(
                UpdateWorker::class.java,
                repeatIntervalSec, TimeUnit.SECONDS
            )
                .setConstraints(constraints)
                .build()
        }
    }
}