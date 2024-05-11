package com.ya.stellarburger.pages;

import org.openqa.selenium.By;

public class OrdersFeedPage {
    public final static By title = By.xpath("//h1[text() = 'Лента заказов']");
    public final static By historyItem = By.xpath("//li[starts-with(@class, 'OrderHistory_listItem')]");
    public final static By recentItems = By.xpath("//ul[starts-with(@class, 'OrderFeed_orderList')]/li[contains(@class, 'text_type_digits')]");
    public final static By total = By.xpath("//p[starts-with(text(), 'Выполнено за все время:')]/following-sibling::p");
    public final static By totalToday = By.xpath("//p[starts-with(text(), 'Выполнено за сегодня:')]/following-sibling::p");
}
