package com.example.sisecevirmece.data

class DatabaseInit(database:SoruDatabase) {
    val dao=database.dao
    init{
        addSorular()
    }

     fun addSorular() {
        dao.addSorular(listOf(
           Soru("Have you ever broken up with someone because you were fat?"),
                   Soru("Has anyone ever caught you having sex?"),
                   Soru("What sexual activity did you refuse to do or endure?"),
                   Soru("When was the last time you had a relationship based solely on physical attraction?"),
                   Soru("If you wanted to have a sexual fantasy with someone from this group other than your partner, what kind of fantasy would you have?"),
                   Soru("What's the biggest lie you've ever told someone while in bed?"),
                   Soru("What do you think is the best and worst thing about group sex?"),
                   Soru("Who was the most sexually satisfying person for you?"),
                   Soru("Have you ever wondered what it would be like to sleep with someone to get a raise?"),
                   Soru("Have you ever had sex with your co-worker?"),
                   Soru("What song would you like to listen to on the night of your crazy sex?"),
                   Soru("What fantasy did you say when you realized 'is that it'?"),
                   Soru("Spit or swallow?"),
                   Soru("Who did you think about the last time you masturba,ted?"),
                   Soru("Describe in full detail the last sexually explicit dream you had."),
                   Soru("Describe in detail an experience you had with your favorite sex toy. Which cartoon character would you like to have sex with?"),
                   Soru("Have you ever watched someone masturbate, do you like it?"),
                   Soru("If you had a threesome, which two would you want to do it with?"),
                   Soru("The sexiest person in this group?"),
                   Soru("How much would you charge for someone to spend the night with you?"),
                   Soru("Other than your partner, whose fantasies would you like to hear from this group?"),
                   Soru("Have you ever dated a friend's ex?"),
                   Soru("How long did your longest orgasm last?"),
                   Soru("Have you ever slept with someone in a relationship?"),
                   Soru("What time of day do you usually masturbate?"),
                   Soru("Do you like to be on top or leave it to your partner?"),
                   Soru("Who from this group is more likely to have a one night stand, why?"),
                   Soru("What's the most daring pick-up phrase you've used or heard used? Did you see someone having sex, did you stay and watch?"),
                   Soru("Which girl pulls the best blowjob?"),
                   Soru("Do you think anyone has ever had sex with you just for the sake of having sex with you?"),
                   Soru("Have you ever ejaculated before you went to the next stage?"),
                   Soru("Hard and fast sex or soft and slow sex?"),
                   Soru("Has anyone told you that you're bad in bed?"),
                   Soru("Would you like to tie in bed?"),
                   Soru("What is the most number of times you masturbate in a single day?"),
                   Soru("Is there anyone you've made love to before in this room right now, who?"),
                   Soru("Where on your partner's body do you find the least sexy?"),
                   Soru("What's the dumbest fight you've been in recently?"),
                   Soru("Would you rather have sex on the beach or in the woods?"),
                   Soru("Have you ever slept with your best friend?"),
                   Soru("Who from this group would you like to spank you?"),
                   Soru("What's your favorite pickup tactic?"),
                   Soru("Have you ever had sex with 2 different people in 24 hours?"),
                   Soru("Have you ever made love underwater?"),
                   Soru("If not, would you like to try?"),
                   Soru("Is there a dangerous situation that seduces you? What's that?"),
                   Soru("What is your favorite arousing thing to hear in bed?")
                //buraya soru ekle
        )
        )
    }
}