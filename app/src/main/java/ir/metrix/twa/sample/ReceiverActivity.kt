package ir.metrix.twa.sample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.metrix.analytics.MetrixAnalytics
import ir.metrix.analytics.messaging.RevenueCurrency

class ReceiverActivity : AppCompatActivity() {

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        when (intent.action) {
            //"intent://metrix?slug=itgsr#Intent;scheme=myscheme;package=my.app.package;action=ir.metrix.NewEvent;end"
            NEW_EVENT -> {
                val slug = intent.data!!.getQueryParameter(KEY_SLUG)!!
                MetrixAnalytics.newEvent(slug)
            }
            //"intent://metrix?slug=itgsr&reveue=1&currency=EUR#Intent;scheme=myscheme;package=my.app.package;action=ir.metrix.NewRevenue;end"
            NEW_REVENUE -> {
                val slug = intent.data!!.getQueryParameter(KEY_SLUG)!!
                val revenue = intent.data!!.getQueryParameter(KEY_REVENUE)!!.toDouble()
                val currency = intent.data!!.getQueryParameter(KEY_CURRENCY)!! // EUR, IRR, USD
                MetrixAnalytics.newRevenue(slug, revenue, RevenueCurrency.valueOf(currency))
            }
            //"intent://metrix?firstName=ali&yourKey=yourValue#Intent;scheme=myscheme;package=my.app.package;action=ir.metrix.UserAttributes;end"
            USER_ATTRIBUTES -> {
                val firstName = intent.data!!.getQueryParameter(KEY_FIRST_NAME)
                if (!firstName.isNullOrEmpty()) {
                    MetrixAnalytics.User.setFirstName(firstName)
                }

                val lastName = intent.data!!.getQueryParameter(KEY_LAST_NAME)
                if (!lastName.isNullOrEmpty()) {
                    MetrixAnalytics.User.setLastName(lastName)
                }

                val email = intent.data!!.getQueryParameter(KEY_EMAIL)
                if (!email.isNullOrEmpty()) {
                    MetrixAnalytics.User.setEmail(email)
                }

                val phoneNumber = intent.data!!.getQueryParameter(KEY_PHONE_NUMBER)
                if (!phoneNumber.isNullOrEmpty()) {
                    MetrixAnalytics.User.setPhoneNumber(phoneNumber)
                }

                val yourValue = intent.data!!.getQueryParameter("yourKey")!!
                MetrixAnalytics.User.setCustomAttribute("yourKey", yourValue)
            }
            //"intent://metrix?id=mammad#Intent;scheme=myscheme;package=my.app.package;action=ir.metrix.UserCustomId;end"
            USER_CUSTOM_ID -> {
                val id = intent.data!!.getQueryParameter(KEY_ID)
                if (id.isNullOrEmpty()) {
                    MetrixAnalytics.User.deleteUserCustomId()
                } else {
                    MetrixAnalytics.User.setUserCustomId(id)
                }
            }
        }

        // in case you want to go back to same screen
        finish()
    }
}

private const val KEY_SLUG = "slug"
private const val KEY_REVENUE = "revenue"
private const val KEY_CURRENCY = "currency"
private const val KEY_ID = "id"
private const val KEY_FIRST_NAME = "firstName"
private const val KEY_LAST_NAME = "lastName"
private const val KEY_EMAIL = "email"
private const val KEY_PHONE_NUMBER = "phoneNumber"

private const val NEW_EVENT = "ir.metrix.NewEvent"
private const val NEW_REVENUE = "ir.metrix.NewRevenue"
private const val USER_ATTRIBUTES = "ir.metrix.UserAttributes"
private const val USER_CUSTOM_ID = "ir.metrix.UserCustomId"