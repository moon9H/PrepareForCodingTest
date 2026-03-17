class Solution {
    static class Light{
		int[] lightTime = new int[3];
		int status;
		int cycle;
		public Light(int green, int yellow, int red) {
			super();
			this.lightTime[0] = green;
			this.lightTime[1] = yellow;
			this.lightTime[2] = red;
			this.status = 0;
			this.cycle = green + yellow + red;
		}
	}
	
	public int solution(int[][] signals) {
        int answer = -1;
        Light[] lights = new Light[signals.length];
        int[] sum = new int[signals.length];
        for (int i = 0; i < signals.length; i++) {
        	lights[i] = new Light(signals[i][0], signals[i][1], signals[i][2]);
        	sum[i] += signals[i][0] + signals[i][1] + signals[i][2];
		}
        
        int LCM = lcmArray(sum);
        
        int t = 0;
        while (t < LCM) {
        	boolean isAllYellow = true;
        	for (int i = 0; i < lights.length; i++) {
				if (lights[i].status != 1) {
					isAllYellow = false;
					break;
				}
			}
        	
        	if (isAllYellow) {
        		answer = t + 1;
        		break;
        	}
        	
        	++t;
        	
        	for (Light light : lights) {
				int cycleTime = t % light.cycle;
				
				if (cycleTime < light.lightTime[0]) {
			        light.status = 0; // green
			    } else if (cycleTime < light.lightTime[0] + light.lightTime[1]) {
			        light.status = 1; // yellow
			    } else {
			        light.status = 2; // red
			    }
			}
        	
        }
        
        return answer;
    }
    
    public static int lcmArray(int[] arr) {
        int result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            result = lcm(result, arr[i]);
        }
        return result;
    }
	
	private static int lcm(int a, int b) {
		return (a * b) / gcd(a, b);
	}
	private static int gcd(int a, int b) {
		if (a % b == 0) {
			return b;
		}
		
		return gcd(b, a % b);
	}
}