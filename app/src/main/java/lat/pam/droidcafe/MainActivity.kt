package lat.pam.droidcafe

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import lat.pam.droidcafe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mOrderMessage: String = ""

    companion object {
        const val EXTRA_ORDER_MESSAGE = "lat.pam.droidcafe.extra.MESSAGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(binding.mainCoordinator) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, OrderActivity::class.java)
            intent.putExtra(EXTRA_ORDER_MESSAGE, mOrderMessage)
            startActivity(intent)
        }

        val contentBinding = binding.contentMain
        contentBinding.donut.setOnClickListener {
            showFoodOrder(getString(R.string.donut_order_message))
        }
        contentBinding.iceCream.setOnClickListener {
            showFoodOrder(getString(R.string.ice_cream_order_message))
        }
        contentBinding.froyo.setOnClickListener {
            showFoodOrder(getString(R.string.froyo_order_message))
        }
    }

    private fun showFoodOrder(message: String) {
        mOrderMessage = message
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
