package com.wangsun.android.test1.api.params

import java.io.Serializable

data class LstGraph(var Receivable: Long, var Payable: Long, var CashFlow: Long, var CFDate: String, var Days: Long){}

data class OBillAlmostDue(var Vendors: Long, var Bills: Long, var PerPay: Long, var BillValue: Long){}

data class OInvAlmostOverdue(var Customers: Long, var Invoices: Long, var PerRec: Long, var InvValue: Long){}

data class OBillOverdue(var Vendors: Long,var Bills: Long,var BillValue: Long,var YetToFollow: Long){}

data class OInvOverdue(var Customers: Long,var Invoices: Long,var InvValue: Long,var YetToFollow: Long){}

data class OPDCIssue(var Vendors: Long,var PDCs: Long,var UpTo7Days: String,var PDCValue: Long){}

data class OPDCReceive(var Customers: Long,var PDCs: Long,var UpTo7Days: String,var PDCValue: Long){}

data class OPendingFollowup(var Customers: Long,var Invoices: Long,var PerRec: Long,var InvValue: Long){}

data class SalesCollections(var SalesYTD: Long,var CollectionsYTD: Long,var SalesMTD: Long,var CollectionsMTD: Long){}


data class ParamData(var Receivable: Long, var Payable: Long, var AvailableBal: Long, var SalesCollection: SalesCollections,
                     var oPendingFollowup: OPendingFollowup, var oPDCReceive: OPDCReceive, var oPDCIssue: OPDCIssue,
                     var oInvOverdue: OInvOverdue, var oBillOverdue: OBillOverdue, var oInvAlmostOverdue: OInvAlmostOverdue,
                     var oBillAlmostDue: OBillAlmostDue, var lstGraph: MutableList<LstGraph>): Serializable {


    companion object {
        val ARG_BUNDLE: String = javaClass.canonicalName + ".bundle_arg"
    }
}