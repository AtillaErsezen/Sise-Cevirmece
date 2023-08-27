package com.example.sisecevirmece.data

class DatabaseInit(database:SoruDatabase) {
    val dao=database.dao
    init{
        addSorular()
    }

     fun addSorular() {
        dao.addSorular(listOf(
            "Have you ever broken up with someone because you were fat?",
        "Has anyone ever caught you having sex?",
        "What sexual activity did you refuse to do or endure?",
        "When was the last time you had a relationship based solely on physical attraction?",
        "If you wanted to have a sexual fantasy with someone from this group other than your partner, what kind of fantasy would you have?",
        "What's the biggest lie you've ever told someone while in bed?",
        "What do you think is the best and worst thing about group sex?",
        "Who was the most sexually satisfying person for you?",
        "Have you ever wondered what it would be like to sleep with someone to get a raise?",
        "Have you ever had sex with your co-worker?",
        "What song would you like to listen to on the night of your crazy sex?",
        "What fantasy did you say when you realized 'is that it'?",
        "spit or swallow?",
        "Who did you think about the last time you masturbated?",
        "Describe in full detail the last sexually explicit dream you had.",
        "Describe in detail an experience you had with your favorite sex toy. Which cartoon character would you like to have sex with?",
        "Have you ever watched someone masturbate, do you like it?",
        "If you had a threesome, which two would you want to do it with?",
        "The sexiest person in this group?",
        "How much would you charge for someone to spend the night with you?",
        "Other than your partner, whose fantasies would you like to hear from this group?",
        "Have you ever dated a friend's ex?",
        "How long did your longest orgasm last?",
        "Have you ever slept with someone in a relationship?",
        "What time of day do you usually masturbate?",
        "Do you like to be on top or leave it to your partner?",
        "Who from this group is more likely to have a one night stand, why?",
        "What's the most daring pick-up phrase you've used or heard used? Did you see someone having sex, did you stay and watch?",
        "Which girl pulls the best blowjob?",
        "Do you think anyone has ever had sex with you just for the sake of having sex with you?",
        "Have you ever ejaculated before you went to the next stage?",
        "hard and fast sex or soft and slow sex?",
        "Has anyone told you that you're bad in bed?",
        "Would you like to tie in bed?",
        "What is the most number of times you masturbate in a single day?",
        "Is there anyone you've made love to before in this room right now, who?",
        "Where on your partner's body do you find the least sexy?",
        "What's the dumbest fight you've been in recently?",
        "Would you rather have sex on the beach or in the woods?",
        "Have you ever slept with your best friend?",
        "Who from this group would you like to spank you?",
        "What's your favorite pickup tactic?",
        "Have you ever had sex with 2 different people in 24 hours?",
        "Have you ever made love underwater?",
        "If not, would you like to try?",
        "Is there a dangerous situation that seduces you? what's that?",
        "What is your favorite arousing thing to hear in bed?"
        )
            //buraya soru ekle
        )
    }
}