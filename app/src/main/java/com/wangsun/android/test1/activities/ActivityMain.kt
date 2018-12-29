package com.wangsun.android.test1.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.wangsun.android.test1.ApplicationMy
import com.wangsun.android.test1.R
import com.wangsun.android.test1.api.RetrofitClient
import com.wangsun.android.test1.api.params.LstGraph
import com.wangsun.android.test1.api.params.ParamData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.expandable_layout.*
import kotlinx.android.synthetic.main.progress_dialog.*
import java.text.ParseException
import java.text.SimpleDateFormat


class ActivityMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initExpandLayout()

        if(ApplicationMy.hasNetwork()){
            getServerData()
        }
        else{
            Toast.makeText(this,"No network connection...",Toast.LENGTH_LONG).show()
        }

    }

    private fun initExpandLayout(){

        id_exp_1.collapse()
        id_exp_2.collapse()
        id_exp_3.collapse()
        id_exp_4.collapse()
        id_exp_5.collapse()
        id_exp_6.collapse()
        id_exp_7.collapse()
        id_exp_8.collapse()

        id_t1.setOnClickListener { id_exp_1.toggle() }
        id_t2.setOnClickListener { id_exp_2.toggle() }
        id_t3.setOnClickListener { id_exp_3.toggle() }
        id_t4.setOnClickListener { id_exp_4.toggle() }
        id_t5.setOnClickListener { id_exp_5.toggle() }
        id_t6.setOnClickListener { id_exp_6.toggle() }
        id_t7.setOnClickListener { id_exp_7.toggle() }
        id_t8.setOnClickListener { id_exp_8.toggle()

        }
    }


    private fun getServerData() {
        id_progress.visibility = View.VISIBLE

        RetrofitClient.ApiData().getData(-5896727554814194598,380218,549)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                id_progress.visibility = View.GONE

                println("Result: ${it}")
                it?.let {
                    setData(it)
                }
            },{
                id_progress.visibility = View.GONE
                println("Error: ${it.message}")
            })
    }

    private fun setData(it: ParamData) {
        id_available_bal.text = it.AvailableBal.toString()
        id_net_received.text = it.Receivable.toString()
        id_net_payable.text = it.Payable.toString()


        id_exp_1_1.text = it.SalesCollection.SalesYTD.toString()
        id_exp_1_2.text = it.SalesCollection.SalesYTD.toString()
        id_exp_1_3.text = it.SalesCollection.SalesMTD.toString()
        id_exp_1_4.text = it.SalesCollection.CollectionsMTD.toString()

        id_exp_2_1.text = it.oPendingFollowup.Customers.toString()
        id_exp_2_2.text = it.oPendingFollowup.Invoices.toString()
        id_exp_2_3.text = it.oPendingFollowup.PerRec.toString()
        id_exp_2_4.text = it.oPendingFollowup.InvValue.toString()

        id_exp_3_1.text = it.oInvOverdue.Customers.toString()
        id_exp_3_2.text = it.oInvOverdue.Invoices.toString()
        id_exp_3_3.text = it.oInvOverdue.InvValue.toString()
        id_exp_3_4.text = it.oInvOverdue.YetToFollow.toString()


        id_exp_4_1.text = it.oInvAlmostOverdue.Customers.toString()
        id_exp_4_2.text = it.oInvAlmostOverdue.Invoices.toString()
        id_exp_4_3.text = it.oInvAlmostOverdue.PerRec.toString()
        id_exp_4_4.text = it.oInvAlmostOverdue.InvValue.toString()


        id_exp_5_1.text = it.oBillOverdue.Vendors.toString()
        id_exp_5_2.text = it.oBillOverdue.Bills.toString()
        id_exp_5_3.text = it.oBillOverdue.BillValue.toString()
        id_exp_5_4.text = it.oBillOverdue.YetToFollow.toString()



        id_exp_6_1.text = it.oBillAlmostDue.Vendors.toString()
        id_exp_6_2.text = it.oBillAlmostDue.Bills.toString()
        id_exp_6_3.text = it.oBillAlmostDue.PerPay.toString()
        id_exp_6_4.text = it.oBillAlmostDue.BillValue.toString()

        id_exp_7_1.text = it.oPDCReceive.Customers.toString()
        id_exp_7_2.text = it.oPDCReceive.PDCs.toString()
        id_exp_7_3.text = it.oPDCReceive.UpTo7Days
        id_exp_7_4.text = it.oPDCReceive.PDCValue.toString()

        id_exp_8_1.text = it.oPDCIssue.Vendors.toString()
        id_exp_8_2.text = it.oPDCIssue.PDCs.toString()
        id_exp_8_3.text = it.oPDCIssue.UpTo7Days
        id_exp_8_4.text = it.oPDCIssue.PDCValue.toString()


        setGraph(it.lstGraph)
    }

    private fun setGraph(graph: MutableList<LstGraph>){

        //1st data
        val entries: ArrayList<Entry> = arrayListOf()

        var j = 0f;

        for(i in graph){
            entries.add(Entry(j, i.Receivable.toFloat()))
            j++
        }

        val dataSet = LineDataSet(entries, "Receivable")
        dataSet.color = ContextCompat.getColor(this, R.color.colorPrimary)
        dataSet.valueTextColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)

        //2nd data

        val entries2: ArrayList<Entry> = arrayListOf()

        var m = 0f

        for(i in graph){
            entries2.add(Entry(m, i.Payable.toFloat()))
            m++
        }

        val dataSet2 = LineDataSet(entries2, "Payable")
        dataSet2.color = ContextCompat.getColor(this, R.color.BlueLight)
        dataSet2.valueTextColor = ContextCompat.getColor(this, R.color.Blue)




        // Controlling X axis
        val xAxis = id_chart.xAxis
        // Set the xAxis position to bottom. Default is top
        xAxis.position = XAxis.XAxisPosition.BOTTOM


        //Customizing x axis value
        val months:MutableList<String> = arrayListOf()

        for(i in graph){
            val customDate = getCustomDate(i.CFDate)
            months.add(customDate)
        }

        val formatter = IAxisValueFormatter { value, axis -> months[value.toInt()] }

        xAxis.granularity = 1f // minimum axis-step (interval) is 1
        xAxis.valueFormatter = formatter


        // Controlling right side of y axis
        val yAxisRight = id_chart.axisRight
        yAxisRight.isEnabled = false

        //***
        // Controlling left side of y axis
        val yAxisLeft = id_chart.axisLeft
        yAxisLeft.granularity = 1f



        // Setting Data
        val data = LineData(dataSet,dataSet2)
        id_chart.data = data
        id_chart.animateX(2500)
        //refresh
        id_chart.invalidate()
    }

    private fun getCustomDate(d1: String): String {

        //convert string date to date
        val format1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        try {
            val date = format1.parse(d1)

            //now get months and year from date
            val format2 = SimpleDateFormat("MM/yyyy")
            return format2.format(date)

        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return "null"
    }







}
