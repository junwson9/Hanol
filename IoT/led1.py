import RPi.GPIO as GPIO
import time

GPIO.setmode(GPIO.BCM)  # Broadcom SoC channel 번호를 사용
# GPIO 핀 번호 설정 (GPIO Pin 13에 연결된 핀을 사용하려면 해당 핀 번호를 사용하세요)
led_pin = 24
GPIO.setup(led_pin, GPIO.OUT)  # LED 제어 핀을 출력으로 설정

try:
    while True:
        GPIO.output(led_pin, True)
        time.sleep(0.0000000000001)
        GPIO.output(led_pin, False)

except KeyboardInterrupt:
    pass

GPIO.cleanup()
