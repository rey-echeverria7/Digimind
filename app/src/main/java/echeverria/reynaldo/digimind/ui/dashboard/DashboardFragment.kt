package echeverria.reynaldo.digimind.ui.dashboard

import android.app.TimePickerDialog
import android.graphics.Insets.add
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import echeverria.reynaldo.digimind.R
import echeverria.reynaldo.digimind.Task
import echeverria.reynaldo.digimind.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import java.util.*


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        root.btn_time.setOnClickListener{
            val cal = Calendar.getInstance()
            val timeSetListener  = TimePickerDialog.OnTimeSetListener{timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY,hour)
                cal.set(Calendar.MINUTE,minute)

                btn_time.text = SimpleDateFormat("HH:mm").format(cal.time)

            }
            TimePickerDialog(root.context, timeSetListener,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),true).show()
        }


        root.btn_save.setOnClickListener{
            var title = et_task.text.toString()
            var time = btn_time.text.toString()

            var days = ArrayList<String>()

            if (checkMonday.isChecked)
                days.add("Monday")
            if (checkTuesday.isChecked)
                days.add("Tuesday")
            if (checkWednesday.isChecked)
                days.add("Wednesday")
            if (checkThursday.isChecked)
                days.add("Thursday")
            if (checkFriday.isChecked)
                days.add("Friday")
            if (checkSatuday.isChecked)
                days.add("Saturday")
            if (checkSunday.isChecked)
                days.add("Sunday")

            var task = Task(title,days,time)

            HomeFragment.tasks.add(task)

            Toast.makeText(root.context,"new task added", Toast.LENGTH_SHORT).show()


        }

        return root
    }
}