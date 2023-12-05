import requests
import threading

SERVER_URL = "http://localhost:5000/submit"
NUM_THREADS = 1000
POST_DATA = {
    'account': '123',
    'value': '1',
    'toAccount': '456',
    'toValue': '500'
}

def send_request():
    try:
        response = requests.post(SERVER_URL, data=POST_DATA)
        if response.status_code == 200:
            print("Transfer successful.")
        else:
            print(f"Error: {response.status_code}, {response.text}")
    except Exception as e:
        print(f"Request failed: {e}")

threads = []
for i in range(NUM_THREADS):
    thread = threading.Thread(target=send_request)
    threads.append(thread)

for thread in threads:
    thread.start()

for thread in threads:
    thread.join()

print("Load test completed.")

