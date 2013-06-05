import org.vertx.java.core.json.impl.Json;

public class Troll {
    	private String name;
    	private int count;
    	
    	public Troll(String name, int count) {
			super();
			this.name = name;
			this.count = count;
		}
    	
		public void denouce() {
			this.count++;
			System.out.println(this);
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		
		public String toJSON() {
			return Json.encode(this);
		}

		@Override
		public String toString() {
			return "Troll [name=" + name + ", count=" + count + "]";
		}
    }