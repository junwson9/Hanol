import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)  # Broadcom SoC channel 번호를 사용
led_pin = 6
GPIO.setup(led_pin, GPIO.OUT)  # LED 제어 핀을 출력으로 설정

try:
    while True:
        GPIO.output(led_pin, True)
        time.sleep(0.0000000000001)
        GPIO.output(led_pin, False)

except KeyboardInterrupt:
    pass

GPIO.cleanup()

