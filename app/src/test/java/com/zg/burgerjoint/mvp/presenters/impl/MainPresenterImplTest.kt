package com.zg.burgerjoint.mvp.presenters.impl

import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zg.burgerjoint.data.model.BurgerModel
import com.zg.burgerjoint.data.model.impls.BurgerModelImpl
import com.zg.burgerjoint.data.model.impls.MockBurgerModelImpl
import com.zg.burgerjoint.data.vos.BurgerVO
import com.zg.burgerjoint.dummy.getDummyBurgers
import com.zg.burgerjoint.mvp.presenters.impls.MainPresenterImpl
import com.zg.burgerjoint.mvp.views.MainView
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class MainPresenterImplTest {

     private lateinit var mPresenter : MainPresenterImpl

     @RelaxedMockK
     private lateinit var mView: MainView

     //private lateinit var mBurgerModelMock : BurgerModel

     @MockK(relaxUnitFun = true)
     private lateinit var mBurgerModelMock : BurgerModel

     @Before
     fun setUp()
     {
        // Mocking setup
        MockKAnnotations.init(this)

//        BurgerModelImpl.init(ApplicationProvider.getApplicationContext())
//         mBurgerModelMock = MockBurgerModelImpl

        mPresenter = MainPresenterImpl()
        mPresenter.initPresenter(mView)
        mPresenter.mBurgerModel = this.mBurgerModelMock


    }

    @Test
    fun onTapAddToCart_callAddBurgerToCart() {

        val tappedBurger = BurgerVO()
        tappedBurger.burgerId = 1
        tappedBurger.burgerName = "The burger of the future"
        tappedBurger.burgerImageUrl = "https://elements.evonik.com/wp-content/uploads/2019/11/burger-fb.jpg"
        tappedBurger.burgerDescription = "Traditional hamburger patties are facing competition from alternatives made of plant-based raw materials or cultivated animal stem cells. This is how they are produced:"

        val imageView = ImageView(ApplicationProvider.getApplicationContext())

        mPresenter.onTapAddToCart(tappedBurger, imageView)

        verify {
                mView.animateAddBurgerToCart(tappedBurger, imageView)
               }
    }

    @Test
    fun onTapCart_callNavigateToCartScreen() {
        mPresenter.onTapCart()
        verify {
            mView.navigateToCartScreen()
        }
    }

    @Test
    fun onTapBurger_callNavigateToBurgerDetails() {
        val tappedBurger = BurgerVO()
        tappedBurger.burgerId = 1
        tappedBurger.burgerName = "The burger of the future"
        tappedBurger.burgerImageUrl = "https://elements.evonik.com/wp-content/uploads/2019/11/burger-fb.jpg"
        tappedBurger.burgerDescription = "Traditional hamburger patties are facing competition from alternatives made of plant-based raw materials or cultivated animal stem cells. This is how they are produced:"

        val imageView = ImageView(ApplicationProvider.getApplicationContext())

        mPresenter.onTapBurger(tappedBurger, imageView)
        verify {
            mView.navigateToBurgerDetailsScreenWithAnimation(tappedBurger.burgerId, imageView)
        }
    }

    @Test
    fun onUIReady_callDisplayBurgerList_callDisplayCountInCart() {

        val lifeCycleOwner = mock(LifecycleOwner::class.java)
        val lifeCycleRegistry = LifecycleRegistry(lifeCycleOwner)
        lifeCycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        `when`(lifeCycleOwner.lifecycle).thenReturn(lifeCycleRegistry)

        mPresenter.onUIReady(lifeCycleOwner)

        verify {
            mView.displayBurgerList(getDummyBurgers())
        }
    }
}