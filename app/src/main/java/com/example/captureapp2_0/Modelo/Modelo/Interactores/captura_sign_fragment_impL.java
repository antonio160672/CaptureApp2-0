package com.example.captureapp2_0.Modelo.Modelo.Interactores;

import com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces.Interactor_captuSig;
import com.example.captureapp2_0.Interfaces.Capta_segnl_interfaces.onlistener_captura_segnas_interf;

public class captura_sign_fragment_impL implements Interactor_captuSig{
    onlistener_captura_segnas_interf listener;

    public captura_sign_fragment_impL(onlistener_captura_segnas_interf listener) {
        this.listener = listener;
    }

    @Override
    public void captura_wifi() {
    }

    @Override
    public void captura_blue() {
    }

    @Override
    public void enviarlistas() {

    }

}
