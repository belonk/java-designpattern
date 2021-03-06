package com.belonk.designpattern.factory.simplefactory;

/**
 * Created by sun on 2020/5/26.
 *
 * @author sunfuchang03@126.com
 * @since 1.0
 */
public class SimpleFactoryPattern {
	//~ Static fields/constants/initializer


	//~ Instance fields


	//~ Constructors


	//~ Methods

	/**
	 * 需求：店铺订购各种类型的牛奶
	 */
	public static void main(String[] args) {
		Store store = new Store();
		Milk  milk  = store.createMilk("pure");
		System.out.println("店铺售出了" + milk.getName());
		milk = store.createMilk("yogurt");
		System.out.println("店铺售出了" + milk.getName());
		milk = store.createMilk("highCalcium");
		System.out.println("店铺售出了" + milk.getName());

		// 生产鲜奶，无法生产
		milk = store.createMilk("freshMilk");
		System.out.println("店铺售出了" + milk.getName());
	}
}

/**
 * 现在，客户端只依赖一个工厂类，不直接依赖具体的牛奶
 */
class Store {
	public Milk sale(String name) {
		System.out.println("顾客购买牛奶");
		System.out.println("  > 生产牛奶");
		Milk milk = createMilk(name);
		System.out.println("  > 收款");
		System.out.println("  > 拿货");
		return milk;
	}

	public Milk createMilk(String name) {
		return MilkOrderFactory.createMilk(name);
	}
}

/**
 * 牛奶订购工厂
 */
class MilkOrderFactory {
	public static Milk createMilk(String name) {
		if ("pure".equals(name)) {
			return new PureMilk();
		} else if ("yogurt".equals(name)) {
			return new Yogurt();
		} else if ("highCalcium".equals(name)) {
			return new HighCalciumMilk();
		} else {
			throw new RuntimeException("无法订购牛奶：" + name);
		}
	}
}

// 牛奶抽象类
abstract class Milk {
	abstract String getName();
}

// 纯牛奶
class PureMilk extends Milk {
	@Override
	String getName() {
		return "纯牛奶";
	}
}

// 酸奶
class Yogurt extends Milk {
	@Override
	String getName() {
		return "酸奶";
	}
}

// 高钙奶
class HighCalciumMilk extends Milk {
	@Override
	String getName() {
		return "高钙奶";
	}
}