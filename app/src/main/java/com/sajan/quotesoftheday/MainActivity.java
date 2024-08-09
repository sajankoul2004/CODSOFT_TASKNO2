package com.sajan.quotesoftheday;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView quoteTextView;
    private Button shareButton, favoriteButton, viewFavoritesButton;
    private List<String> favoriteQuotes;
    private String currentQuote;
    private String[] quotes = {
            "The only way to do great work is to love what you do. - Steve Jobs",
            "Life is what happens when you're busy making other plans. - John Lennon",
            "The purpose of our lives is to be happy. - Dalai Lama",
            "Get busy living or get busy dying. - Stephen King",
            "You have within you right now, everything you need to deal with whatever the world can throw at you. - Brian Tracy",
            "The best way to predict your future is to create it. - Peter Drucker",
            "Your time is limited, don't waste it living someone else's life. - Steve Jobs",
            "In the end, we will remember not the words of our enemies, but the silence of our friends. - Martin Luther King Jr.",
            "Do not go gentle into that good night. Rage, rage against the dying of the light. - Dylan Thomas",
            "You miss 100% of the shots you don't take. - Wayne Gretzky",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "The greatest glory in living lies not in never falling, but in rising every time we fall. - Nelson Mandela",
            "The way to get started is to quit talking and begin doing. - Walt Disney",
            "Believe you can and you're halfway there. - Theodore Roosevelt",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "It does not matter how slowly you go as long as you do not stop. - Confucius",
            "Life is either a daring adventure or nothing at all. - Helen Keller",
            "The only impossible journey is the one you never begin. - Tony Robbins",
            "Everything you’ve ever wanted is on the other side of fear. - George Addair",
            "Opportunities don't happen. You create them. - Chris Grosser",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t watch the clock; do what it does. Keep going. - Sam Levenson",
            "The harder I work, the luckier I get. - Samuel Goldwyn",
            "I find that the harder I work, the more luck I seem to have. - Thomas Jefferson",
            "To be successful, you must accept all challenges that come your way. You can’t just accept the ones you like. - Mike Gafka",
            "Success is not the key to happiness. Happiness is the key to success. If you love what you are doing, you will be successful. - Albert Schweitzer",
            "The best revenge is massive success. - Frank Sinatra",
            "I have not failed. I've just found 10,000 ways that won't work. - Thomas Edison",
            "Success is walking from failure to failure with no loss of enthusiasm. - Winston Churchill",
            "Don't be pushed around by the fears in your mind. Be led by the dreams in your heart. - Roy T. Bennett",
            "You can't help everyone, but everyone can help someone. - Ronald Reagan",
            "What lies behind us and what lies before us are tiny matters compared to what lies within us. - Ralph Waldo Emerson",
            "Your life does not get better by chance, it gets better by change. - Jim Rohn",
            "Success is not final, failure is not fatal: It is the courage to continue that counts. - Winston Churchill",
            "Hardships often prepare ordinary people for an extraordinary destiny. - C.S. Lewis",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "It is never too late to be what you might have been. - George Eliot",
            "The only person you are destined to become is the person you decide to be. - Ralph Waldo Emerson",
            "Act as if what you do makes a difference. It does. - William James",
            "In three words I can sum up everything I've learned about life: it goes on. - Robert Frost",
            "The best time to plant a tree was 20 years ago. The second-best time is now. - Chinese Proverb",
            "Happiness is not something ready made. It comes from your own actions. - Dalai Lama",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson",
            "In order to succeed, we must first believe that we can. - Nikos Kazantzakis",
            "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
            "The mind is everything. What you think you become. - Buddha",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "You don't have to be great to start, but you have to start to be great. - Zig Ziglar",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
            "Don’t let what you cannot do interfere with what you can do. - John R. Wooden",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "It is not the strongest of the species that survive, nor the most intelligent, but the one most responsive to change. - Charles Darwin",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t let the noise of others’ opinions drown out your own inner voice. - Steve Jobs",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson",
            "In order to succeed, we must first believe that we can. - Nikos Kazantzakis",
            "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
            "The mind is everything. What you think you become. - Buddha",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "You don't have to be great to start, but you have to start to be great. - Zig Ziglar",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
            "Don’t let what you cannot do interfere with what you can do. - John R. Wooden",
            "You can't help everyone, but everyone can help someone. - Ronald Reagan",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "It is not the strongest of the species that survive, nor the most intelligent, but the one most responsive to change. - Charles Darwin",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t let the noise of others’ opinions drown out your own inner voice. - Steve Jobs",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson",
            "In order to succeed, we must first believe that we can. - Nikos Kazantzakis",
            "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
            "The mind is everything. What you think you become. - Buddha",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "You don't have to be great to start, but you have to start to be great. - Zig Ziglar",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
            "Don’t let what you cannot do interfere with what you can do. - John R. Wooden",
            "You can't help everyone, but everyone can help someone. - Ronald Reagan",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "It is not the strongest of the species that survive, nor the most intelligent, but the one most responsive to change. - Charles Darwin",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t let the noise of others’ opinions drown out your own inner voice. - Steve Jobs",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson",
            "In order to succeed, we must first believe that we can. - Nikos Kazantzakis",
            "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
            "The mind is everything. What you think you become. - Buddha",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "You don't have to be great to start, but you have to start to be great. - Zig Ziglar",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
            "Don’t let what you cannot do interfere with what you can do. - John R. Wooden",
            "You can't help everyone, but everyone can help someone. - Ronald Reagan",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "It is not the strongest of the species that survive, nor the most intelligent, but the one most responsive to change. - Charles Darwin",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t let the noise of others’ opinions drown out your own inner voice. - Steve Jobs",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson",
            "In order to succeed, we must first believe that we can. - Nikos Kazantzakis",
            "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
            "The mind is everything. What you think you become. - Buddha",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "You don't have to be great to start, but you have to start to be great. - Zig Ziglar",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
            "Don’t let what you cannot do interfere with what you can do. - John R. Wooden",
            "You can't help everyone, but everyone can help someone. - Ronald Reagan",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "It is not the strongest of the species that survive, nor the most intelligent, but the one most responsive to change. - Charles Darwin",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t let the noise of others’ opinions drown out your own inner voice. - Steve Jobs",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson",
            "In order to succeed, we must first believe that we can. - Nikos Kazantzakis",
            "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
            "The mind is everything. What you think you become. - Buddha",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "You don't have to be great to start, but you have to start to be great. - Zig Ziglar",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
            "Don’t let what you cannot do interfere with what you can do. - John R. Wooden",
            "You can't help everyone, but everyone can help someone. - Ronald Reagan",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "It is not the strongest of the species that survive, nor the most intelligent, but the one most responsive to change. - Charles Darwin",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t let the noise of others’ opinions drown out your own inner voice. - Steve Jobs",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson",
            "In order to succeed, we must first believe that we can. - Nikos Kazantzakis",
            "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
            "The mind is everything. What you think you become. - Buddha",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "You don't have to be great to start, but you have to start to be great. - Zig Ziglar",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
            "Don’t let what you cannot do interfere with what you can do. - John R. Wooden",
            "You can't help everyone, but everyone can help someone. - Ronald Reagan",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "It is not the strongest of the species that survive, nor the most intelligent, but the one most responsive to change. - Charles Darwin",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t let the noise of others’ opinions drown out your own inner voice. - Steve Jobs",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson",
            "In order to succeed, we must first believe that we can. - Nikos Kazantzakis",
            "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
            "The mind is everything. What you think you become. - Buddha",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "You don't have to be great to start, but you have to start to be great. - Zig Ziglar",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
            "Don’t let what you cannot do interfere with what you can do. - John R. Wooden",
            "You can't help everyone, but everyone can help someone. - Ronald Reagan",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "It is not the strongest of the species that survive, nor the most intelligent, but the one most responsive to change. - Charles Darwin",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t let the noise of others’ opinions drown out your own inner voice. - Steve Jobs",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson",
            "In order to succeed, we must first believe that we can. - Nikos Kazantzakis",
            "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
            "The mind is everything. What you think you become. - Buddha",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "You don't have to be great to start, but you have to start to be great. - Zig Ziglar",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
            "Don’t let what you cannot do interfere with what you can do. - John R. Wooden",
            "You can't help everyone, but everyone can help someone. - Ronald Reagan",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "It is not the strongest of the species that survive, nor the most intelligent, but the one most responsive to change. - Charles Darwin",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t let the noise of others’ opinions drown out your own inner voice. - Steve Jobs",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson",
            "In order to succeed, we must first believe that we can. - Nikos Kazantzakis",
            "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
            "The mind is everything. What you think you become. - Buddha",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "You don't have to be great to start, but you have to start to be great. - Zig Ziglar",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
            "Don’t let what you cannot do interfere with what you can do. - John R. Wooden",
            "You can't help everyone, but everyone can help someone. - Ronald Reagan",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "It is not the strongest of the species that survive, nor the most intelligent, but the one most responsive to change. - Charles Darwin",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t let the noise of others’ opinions drown out your own inner voice. - Steve Jobs",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson",
            "In order to succeed, we must first believe that we can. - Nikos Kazantzakis",
            "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
            "The mind is everything. What you think you become. - Buddha",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "You don't have to be great to start, but you have to start to be great. - Zig Ziglar",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
            "Don’t let what you cannot do interfere with what you can do. - John R. Wooden",
            "You can't help everyone, but everyone can help someone. - Ronald Reagan",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "It is not the strongest of the species that survive, nor the most intelligent, but the one most responsive to change. - Charles Darwin",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t let the noise of others’ opinions drown out your own inner voice. - Steve Jobs",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson",
            "In order to succeed, we must first believe that we can. - Nikos Kazantzakis",
            "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
            "The mind is everything. What you think you become. - Buddha",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "You don't have to be great to start, but you have to start to be great. - Zig Ziglar",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
            "Don’t let what you cannot do interfere with what you can do. - John R. Wooden",
            "You can't help everyone, but everyone can help someone. - Ronald Reagan",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "It is not the strongest of the species that survive, nor the most intelligent, but the one most responsive to change. - Charles Darwin",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t let the noise of others’ opinions drown out your own inner voice. - Steve Jobs",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson",
            "In order to succeed, we must first believe that we can. - Nikos Kazantzakis",
            "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
            "The mind is everything. What you think you become. - Buddha",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "You don't have to be great to start, but you have to start to be great. - Zig Ziglar",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
            "Don’t let what you cannot do interfere with what you can do. - John R. Wooden",
            "You can't help everyone, but everyone can help someone. - Ronald Reagan",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "It is not the strongest of the species that survive, nor the most intelligent, but the one most responsive to change. - Charles Darwin",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t let the noise of others’ opinions drown out your own inner voice. - Steve Jobs",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson",
            "In order to succeed, we must first believe that we can. - Nikos Kazantzakis",
            "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
            "The mind is everything. What you think you become. - Buddha",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "You don't have to be great to start, but you have to start to be great. - Zig Ziglar",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
            "Don’t let what you cannot do interfere with what you can do. - John R. Wooden",
            "You can't help everyone, but everyone can help someone. - Ronald Reagan",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "It is not the strongest of the species that survive, nor the most intelligent, but the one most responsive to change. - Charles Darwin",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t let the noise of others’ opinions drown out your own inner voice. - Steve Jobs",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson",
            "In order to succeed, we must first believe that we can. - Nikos Kazantzakis",
            "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
            "The mind is everything. What you think you become. - Buddha",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "You don't have to be great to start, but you have to start to be great. - Zig Ziglar",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
            "Don’t let what you cannot do interfere with what you can do. - John R. Wooden",
            "You can't help everyone, but everyone can help someone. - Ronald Reagan",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "It is not the strongest of the species that survive, nor the most intelligent, but the one most responsive to change. - Charles Darwin",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t let the noise of others’ opinions drown out your own inner voice. - Steve Jobs",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson",
            "In order to succeed, we must first believe that we can. - Nikos Kazantzakis",
            "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
            "The mind is everything. What you think you become. - Buddha",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "You don't have to be great to start, but you have to start to be great. - Zig Ziglar",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
            "Don’t let what you cannot do interfere with what you can do. - John R. Wooden",
            "You can't help everyone, but everyone can help someone. - Ronald Reagan",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "It is not the strongest of the species that survive, nor the most intelligent, but the one most responsive to change. - Charles Darwin",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t let the noise of others’ opinions drown out your own inner voice. - Steve Jobs",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson",
            "In order to succeed, we must first believe that we can. - Nikos Kazantzakis",
            "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
            "The mind is everything. What you think you become. - Buddha",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "You don't have to be great to start, but you have to start to be great. - Zig Ziglar",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
            "Don’t let what you cannot do interfere with what you can do. - John R. Wooden",
            "You can't help everyone, but everyone can help someone. - Ronald Reagan",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "It is not the strongest of the species that survive, nor the most intelligent, but the one most responsive to change. - Charles Darwin",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t let the noise of others’ opinions drown out your own inner voice. - Steve Jobs",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson",
            "In order to succeed, we must first believe that we can. - Nikos Kazantzakis",
            "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
            "The mind is everything. What you think you become. - Buddha",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "You don't have to be great to start, but you have to start to be great. - Zig Ziglar",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
            "Don’t let what you cannot do interfere with what you can do. - John R. Wooden",
            "You can't help everyone, but everyone can help someone. - Ronald Reagan",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "It is not the strongest of the species that survive, nor the most intelligent, but the one most responsive to change. - Charles Darwin",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t let the noise of others’ opinions drown out your own inner voice. - Steve Jobs",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson",
            "In order to succeed, we must first believe that we can. - Nikos Kazantzakis",
            "Life is 10% what happens to us and 90% how we react to it. - Charles R. Swindoll",
            "The mind is everything. What you think you become. - Buddha",
            "The only thing standing between you and your goal is the story you keep telling yourself as to why you can't achieve it. - Jordan Belfort",
            "Don’t let yesterday take up too much of today. - Will Rogers",
            "You don't have to be great to start, but you have to start to be great. - Zig Ziglar",
            "The pessimist sees difficulty in every opportunity. The optimist sees opportunity in every difficulty. - Winston Churchill",
            "Don’t let what you cannot do interfere with what you can do. - John R. Wooden",
            "You can't help everyone, but everyone can help someone. - Ronald Reagan",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "It is not the strongest of the species that survive, nor the most intelligent, but the one most responsive to change. - Charles Darwin",
            "Your time is limited, so don't waste it living someone else's life. - Steve Jobs",
            "The best way to find yourself is to lose yourself in the service of others. - Mahatma Gandhi",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Don’t let the noise of others’ opinions drown out your own inner voice. - Steve Jobs",
            "We may encounter many defeats but we must not be defeated. - Maya Angelou",
            "We can't help everyone, but everyone can help someone. - Ronald Reagan",
            "Your work is going to fill a large part of your life, and the only way to be truly satisfied is to do what you believe is great work. - Steve Jobs",
            "You do not find the happy life. You make it. - Camilla E. Kimball",
            "The only way to achieve the impossible is to believe it is possible. - Charles Kingsleigh",
            "The secret of getting ahead is getting started. - Mark Twain",
            "Don’t be afraid to give up the good to go for the great. - John D. Rockefeller",
            "If you want something you’ve never had, you must be willing to do something you’ve never done. - Thomas Jefferson",
            "It is not length of life, but depth of life. - Ralph Waldo Emerson",
            "Success is not measured by what you accomplish, but the opposition you have encountered, and the courage with which you have maintained the struggle against overwhelming odds. - Orison Swett Marden",
            "The only way to have a friend is to be one. - Ralph Waldo Emerson"
    };

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteTextView = findViewById(R.id.quoteTextView);
        shareButton = findViewById(R.id.shareButton);
        favoriteButton = findViewById(R.id.favoriteButton);
        viewFavoritesButton = findViewById(R.id.viewFavoritesButton);
        mAdView = findViewById(R.id.adView);

        // Initialize AdMob
        MobileAds.initialize(this, initializationStatus -> {});

        // Load an ad into the AdView
        AdRequest adRequest = new AdRequest.Builder()

                .build();
        mAdView.loadAd(adRequest);


        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Log the error
                Log.e("AdError", "Ad failed to load: " + adError.getMessage());
                Toast.makeText(getApplicationContext(), "Ad failed to load: " + adError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        // Load favorite quotes from SharedPreferences
        loadFavoriteQuotes();

        // Display today's quote or load it from SharedPreferences if already set
        checkDailyQuote();

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, currentQuote);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!favoriteQuotes.contains(currentQuote)) {
                    favoriteQuotes.add(currentQuote);
                    saveFavoriteQuotes();
                    Toast.makeText(MainActivity.this, "Quote added to favorites", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Quote is already in favorites", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                intent.putStringArrayListExtra("favoriteQuotes", (ArrayList<String>) favoriteQuotes);
                startActivity(intent);
            }
        });
    }

    private void checkDailyQuote() {
        SharedPreferences prefs = getSharedPreferences("QuotePrefs", MODE_PRIVATE);
        String lastQuoteDate = prefs.getString("lastQuoteDate", null);
        String todayDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        if (!todayDate.equals(lastQuoteDate)) {
            Random random = new Random();
            currentQuote = quotes[random.nextInt(quotes.length)];
            quoteTextView.setText(currentQuote);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("lastQuoteDate", todayDate);
            editor.putString("currentQuote", currentQuote);
            editor.apply();
        } else {
            currentQuote = prefs.getString("currentQuote", quotes[0]);
            quoteTextView.setText(currentQuote);
        }
    }

    private void loadFavoriteQuotes() {
        SharedPreferences prefs = getSharedPreferences("QuotePrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("favoriteQuotes", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        favoriteQuotes = gson.fromJson(json, type);

        if (favoriteQuotes == null) {
            favoriteQuotes = new ArrayList<>();
        }
    }

    private void saveFavoriteQuotes() {
        SharedPreferences prefs = getSharedPreferences("QuotePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(favoriteQuotes);
        editor.putString("favoriteQuotes", json);
        editor.apply();
    }
}
