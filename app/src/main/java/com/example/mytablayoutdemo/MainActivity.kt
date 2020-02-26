package com.example.mytablayoutdemo

import android.animation.ValueAnimator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sdsmdg.harjot.vectormaster.VectorMasterView
import com.sdsmdg.harjot.vectormaster.models.PathModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId){

            R.id.action_cart -> {
                draw(6)
                lin_id.x = bottom_nav.mFirstCurveControlPoint1.x.toFloat()
                lin_id.x = bottom_nav.mSecondCurveControlPoint1.x.toFloat()
                fab.visibility = View.VISIBLE
                fab1.visibility = View.GONE
                fab2.visibility = View.GONE
                drawAnimation(fab)

                //Event
                Toast.makeText(this,"cliked on Boy Names",Toast.LENGTH_SHORT).show()

            }
            R.id.action_shipping -> {
                draw(2)
                lin_id.x = bottom_nav.mFirstCurveControlPoint1.x.toFloat()
                lin_id.x = bottom_nav.mSecondCurveControlPoint1.x.toFloat()
                fab.visibility = View.GONE
                fab1.visibility = View.VISIBLE
                fab2.visibility = View.GONE
                drawAnimation(fab1)

                //Event
                Toast.makeText(this,"cliked on Both of Names",Toast.LENGTH_SHORT).show()


            }
            R.id.action_customer -> {
                draw()
                lin_id.x = bottom_nav.mFirstCurveControlPoint1.x.toFloat()
                lin_id.x = bottom_nav.mSecondCurveControlPoint1.x.toFloat()
                fab.visibility = View.GONE
                fab1.visibility = View.GONE
                fab2.visibility = View.VISIBLE
                drawAnimation(fab2)

                //Event
                Toast.makeText(this,"cliked on Girl Names",Toast.LENGTH_SHORT).show()


            }

        }
        return true

    }

    private fun drawAnimation(fab: VectorMasterView?) {
        outLine = fab!!.getPathModelByName("outline")
        outLine.strokeColor = Color.WHITE
        outLine.trimPathEnd = 0f

        val valueAnimator = ValueAnimator.ofFloat(0.0f,1.0f)

        valueAnimator.duration = 1000
        valueAnimator.addUpdateListener { valueAnimator ->
            outLine.trimPathEnd = valueAnimator.animatedValue as Float
            fab.update()
        }
        valueAnimator.start()





    }

    private fun draw() {
        bottom_nav.mFirstCurveStartPoint.set(bottom_nav.mNavigationBarWidth *10/ 12
                - bottom_nav.CURVE_CIRCLE_RADIUS*2 - bottom_nav.CURVE_CIRCLE_RADIUS/3,
            0)
        //The coorrdinates of endpoint aftre curve
        bottom_nav.mFirstCurveEndPoint.set(bottom_nav.mNavigationBarWidth*10/12,
            bottom_nav.CURVE_CIRCLE_RADIUS + bottom_nav.CURVE_CIRCLE_RADIUS/4)

        //same thin for second
        bottom_nav.mSecondCurveStartPoint = bottom_nav.mFirstCurveStartPoint

        bottom_nav.mSecondCurveEndPoint.set(bottom_nav.mNavigationBarWidth *10/12
                + bottom_nav.CURVE_CIRCLE_RADIUS *2 + bottom_nav.CURVE_CIRCLE_RADIUS/3,0)

        //The coordinate of first control point on a cubic curve
        bottom_nav.mSecondCurveControlPoint1.set(bottom_nav.mFirstCurveStartPoint.x
                + bottom_nav.CURVE_CIRCLE_RADIUS + bottom_nav.CURVE_CIRCLE_RADIUS/4,
            bottom_nav.mFirstCurveStartPoint.y)

        //The coordinate of second control point on cubic curve
        bottom_nav.mFirstCurveControlPoint2.set(bottom_nav.mFirstCurveEndPoint.x -
                bottom_nav.CURVE_CIRCLE_RADIUS * 2 + bottom_nav.CURVE_CIRCLE_RADIUS,
            bottom_nav.mFirstCurveEndPoint.y)

        //second
        bottom_nav.mSecondCurveControlPoint1.set(bottom_nav.mSecondCurveStartPoint.x
                + bottom_nav.CURVE_CIRCLE_RADIUS * 2 - bottom_nav.CURVE_CIRCLE_RADIUS,
            bottom_nav.mSecondCurveStartPoint.y)

        bottom_nav.mSecondCurveControlPoint2.set(bottom_nav.mSecondCurveEndPoint.x
                -(bottom_nav.CURVE_CIRCLE_RADIUS + bottom_nav.CURVE_CIRCLE_RADIUS/4),
            bottom_nav.mSecondCurveEndPoint.y)


    }

    private fun draw(i: Int) {
        bottom_nav.mFirstCurveStartPoint.set(bottom_nav.mNavigationBarWidth / i
        - bottom_nav.CURVE_CIRCLE_RADIUS*2 - bottom_nav.CURVE_CIRCLE_RADIUS/3,
            0)
        //The coorrdinates of endpoint aftre curve
        bottom_nav.mFirstCurveEndPoint.set(bottom_nav.mNavigationBarWidth/i,
            bottom_nav.CURVE_CIRCLE_RADIUS + bottom_nav.CURVE_CIRCLE_RADIUS/4)

        //same thin for second
        bottom_nav.mSecondCurveStartPoint = bottom_nav.mFirstCurveStartPoint

        bottom_nav.mSecondCurveEndPoint.set(bottom_nav.mNavigationBarWidth/ i
        + bottom_nav.CURVE_CIRCLE_RADIUS *2 + bottom_nav.CURVE_CIRCLE_RADIUS/3,0)

        //The coordinate of first control point on a cubic curve
        bottom_nav.mSecondCurveControlPoint1.set(bottom_nav.mFirstCurveStartPoint.x
        + bottom_nav.CURVE_CIRCLE_RADIUS + bottom_nav.CURVE_CIRCLE_RADIUS/4,
            bottom_nav.mFirstCurveStartPoint.y)

        //The coordinate of second control point on cubic curve
        bottom_nav.mFirstCurveControlPoint2.set(bottom_nav.mFirstCurveEndPoint.x -
        bottom_nav.CURVE_CIRCLE_RADIUS * 2 + bottom_nav.CURVE_CIRCLE_RADIUS,
            bottom_nav.mFirstCurveEndPoint.y)

        //second
        bottom_nav.mSecondCurveControlPoint1.set(bottom_nav.mSecondCurveStartPoint.x
        + bottom_nav.CURVE_CIRCLE_RADIUS * 2 - bottom_nav.CURVE_CIRCLE_RADIUS,
            bottom_nav.mSecondCurveStartPoint.y)

        bottom_nav.mSecondCurveControlPoint2.set(bottom_nav.mSecondCurveEndPoint.x
        -(bottom_nav.CURVE_CIRCLE_RADIUS + bottom_nav.CURVE_CIRCLE_RADIUS/4),
            bottom_nav.mSecondCurveEndPoint.y)


    }


    internal lateinit var outLine:PathModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_nav.inflateMenu(R.menu.main_menu) //need first

        bottom_nav.selectedItemId = R.id.action_shipping //need second

        bottom_nav.setOnNavigationItemSelectedListener(this@MainActivity) //need third



    }


}
