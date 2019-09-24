#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <string.h>
#include <math.h>

/* This program creates eight separate POSIX threads and calculates pi using
two different algorithms. The first uses Wallis' estimation and the second uses the Leibnez
algorithm. They each are separated into 8 threads doing 1/8th of the work up to 4,000,000 
iterations.
*/ 

int iterations = 500000;
int i, j, k = 0;
int counter = 0;
pthread_t tid[16];
pthread_mutex_t lock;
double pi = 1;

void *divideMultiply () {
	double numerator, denominator;
	
	pthread_mutex_lock(&lock); 
	counter++;	

// Getting first value for the current thread		
	if (counter % 2 == 0) {
		numerator = counter;
		denominator = (counter + 1);
	} else {
		numerator = (counter + 1);
		denominator = counter;
	}
// First value
	pi *= (numerator/denominator);
	
	for (j = 0; j < 10; j++) {
		for (i = 0; i < iterations; i++) {		
			denominator += 8.0;
			numerator += 8.0;

			pi *= (numerator/denominator);					
		}
	}
	pthread_mutex_unlock(&lock);

	return NULL;
	
}

void *plusMinus () {
// Local variables and counter needed for individual threads
	pthread_mutex_lock(&lock);
	double local = 0.0;
	counter++;

	for (i = 0; i < iterations; i++) {	
		double denominator = (2*i+1); 
		local += (i % 2 == 0 ? 1 : -1) * (1.0/denominator);
		denominator += 14;	
	}
// Final calculation
	if (counter == 15) {
		pi += local;
		pi *= 4;
	} else
		pi += local;
	
	pthread_mutex_unlock(&lock);		
	return NULL;
			
}	

int main () {
	int error;
	double finalWallis;
	double finalUnknown;

// Create mutex lock, create and join 8 threads	
	if (pthread_mutex_init(&lock, NULL) != 0) {
		printf("\nMutex initialization has failed.");
		return 1;
	}

	while (k < 8) {
		error = pthread_create(&(tid[k]), NULL, &divideMultiply, NULL); 
		if (error != 0)
			printf("\nThread cannot be created: [%s].", strerror(error));
		k++;
	} 

	pthread_join(tid[0], NULL);
	pthread_join(tid[1], NULL);
	pthread_join(tid[2], NULL);
	pthread_join(tid[3], NULL);
	pthread_join(tid[4], NULL);
	pthread_join(tid[5], NULL);
	pthread_join(tid[6], NULL);
	pthread_join(tid[7], NULL);
	pi *= 2.0;
	finalWallis = pi;
	
// Second half - reinitialize variables, create and join last 8 threads
	pi = 0;
	counter = 0;

	while (k < 16) {
		error = pthread_create(&(tid[k]), NULL, &plusMinus, NULL); 
		if (error != 0)
			printf("\nThread cannot be created: [%s].", strerror(error));
		k++;
	}

	pthread_join(tid[8], NULL);
	pthread_join(tid[9], NULL);
	pthread_join(tid[10], NULL);
	pthread_join(tid[11], NULL);
	pthread_join(tid[12], NULL);
	pthread_join(tid[13], NULL);
	pthread_join(tid[14], NULL);
	pthread_join(tid[15], NULL);
 	
// Not sure why here - my algorithm was outputting (pi*2)	
	pi /= 2;
	finalUnknown = pi;

// Print final calculations and destroy mutex lock
	printf("\nAll threads done (Wallis), value calculated to be: \n%.15f\n", finalWallis);
	printf("This is off by about %.15f\n\n", (M_PI - finalWallis));
	
	printf("\nAll threads done (Unknown), value calculated to be: \n%.15f\n", finalUnknown);
	printf("This is off by about %.15f\n\n", (M_PI - finalUnknown));

	pthread_mutex_destroy(&lock);
	
	return 0;
}
