package com.ronzhin.tips.patterns.structural.decorator;

import com.ronzhin.tips.patterns.structural.decorator.core.DataSource;
import com.ronzhin.tips.patterns.structural.decorator.core.DataSourceDecoratorAdder;
import com.ronzhin.tips.patterns.structural.decorator.core.DataSourceDecoratorMultiplicator;
import com.ronzhin.tips.patterns.structural.decorator.core.DataSourceImpl;

/**
 * @author sergey
 * created on 16.01.19.
 */
public class Demo {
  public static void main(String[] args) {
    DataSource ds = new DataSourceImpl();
    printer(ds);

    printer(new DataSourceDecoratorAdder(ds));
    printer(new DataSourceDecoratorMultiplicator(ds));
    printer(new DataSourceDecoratorAdder(new DataSourceDecoratorMultiplicator(ds)));

  }

  private static void printer(DataSource ds) {
    System.out.println(ds.getInteger());
  }
}
