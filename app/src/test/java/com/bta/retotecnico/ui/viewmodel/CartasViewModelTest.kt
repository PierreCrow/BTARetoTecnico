package com.bta.retotecnico.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bta.retotecnico.domain.model.ConsultaEstadoJuego
import com.bta.retotecnico.usercases.CartasUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CartasViewModelTest {

    @RelaxedMockK
    private lateinit var cartasUseCase: CartasUseCase
    private lateinit var cartasViewModel: CartasViewModel
    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        cartasViewModel = CartasViewModel(cartasUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test viewmodel cuando se crea y consulto la funcion para ver el estado del juego`() = runTest{
        //Given
        val testt = ConsultaEstadoJuego(0)
        coEvery { cartasUseCase() } returns testt
        //When
        cartasViewModel.consultaEstadoJuego()
        //Then
        assert(cartasViewModel.responseConsultaEstado.value == testt)
    }

}