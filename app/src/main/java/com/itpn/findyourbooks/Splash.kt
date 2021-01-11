package com.itpn.findyourbooks

import android.content.Intent
import com.daimajia.androidanimations.library.Techniques
import com.viksaa.sssplash.lib.activity.AwesomeSplash
import com.viksaa.sssplash.lib.cnst.Flags
import com.viksaa.sssplash.lib.model.ConfigSplash

class Splash : AwesomeSplash() {
    override fun initSplash(configSplash: ConfigSplash?) {

        //Customize circular reveal
        configSplash?.backgroundColor = R.color.primaryLightColor
        configSplash?.animCircularRevealDuration = 2000 //milisegundos
        configSplash?.revealFlagX = Flags.REVEAL_RIGHT
        configSplash?.revealFlagY = Flags.REVEAL_BOTTOM

        //Customize logo
        configSplash?.logoSplash = R.drawable.ic_launcher_foreground
        configSplash?.animLogoSplashDuration = 2000 //milisegundos
        configSplash?.animLogoSplashTechnique = Techniques.Bounce

        configSplash?.titleSplash = "Find Your Books 📕📗📘📚"
        configSplash?.titleTextSize = 16f //textSize en float
        configSplash?.animLogoSplashDuration = 2000 // milisegundos
    }

    override fun animationsFinished() {
        intent = Intent(this,RegisterPerson::class.java)
        startActivity(intent)
    }
}