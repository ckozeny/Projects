#include <netdb.h> 
#include <netinet/in.h> 
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h> 
#include <stdio.h>
#include <string.h> 
#include <sys/socket.h> 
#include <sys/types.h> 
#define MAX 1025 
#define PORT 8080 
#define SA struct sockaddr 

struct student {
    char *lname, *initial, *fname;
    int SID;
    float GPA;
    struct student *next;
};

typedef struct student SREC;
SREC *front, *ptr;

char *printRecords() {
	char *buffer = malloc(MAX);
	int n = 0;

	if (front == NULL)
    {
        strncpy(buffer, "**No Students in the database to display**", 44);	
		return buffer;
	}
    else
    {
        for (ptr = front;ptr != NULL;ptr = ptr->next)
        { 
			snprintf(buffer + strlen(buffer), MAX, "\n%s\n%s\n%s\n%d\n%f\n", ptr->fname, ptr->initial, ptr->lname, ptr->SID, ptr->GPA);	
		}
    }
	return buffer;
}

char *fnameSort(SREC **head)
{   
	int done = 0;

    if (front == NULL) {
        return printRecords();
    }
    
	while (!done) {
        SREC **pv = head;
        SREC *nd = *head;
        SREC *nx = (*head)->next;

        done = 1;

        while (nx) {
            if (strcmp(nd->fname, nx->fname) > 0) {
                nd->next = nx->next;
                nx->next = nd;
                *pv = nx;

                done = 0;
            }
            pv = &nd->next;
            nd = nx;
            nx = nx->next;
        }
    }
	return printRecords();
}

char *lnameSort(SREC **head)
{
	int done = 0;

    if (front == NULL) {
		return printRecords();
    }
    
	while (!done) {
        SREC **pv = head;
        SREC *nd = *head;
        SREC *nx = (*head)->next;

        done = 1;

        while (nx) {
            if (strcmp(nd->lname, nx->lname) > 0) {
                nd->next = nx->next;
                nx->next = nd;
                *pv = nx;

                done = 0;
            }
            pv = &nd->next;
            nd = nx;
            nx = nx->next;
        }
    }
    return printRecords();
}

char *GPAsort () {
    int tmp;
    SREC *temp;

    if (front == NULL) {
		return printRecords(); 
    }
    else
    {
        for (ptr = front;ptr != NULL;ptr = ptr->next)
        {
            for (temp = ptr->next;temp != NULL;temp = temp->next)
            {
                if (ptr->GPA < temp->GPA)
                {
                    tmp = ptr->GPA;
                    ptr->GPA = temp->GPA;
                    temp->GPA = tmp;
                }
            }
        }
        return printRecords();
    }
}

int deleteRecord (SREC **front, char *command) {
    char *temp[10];
	int i = 0;

	if (!front || !(*front))
        return 1;
	
	char *c = strtok(command, " ");

    while (c != NULL) {
        temp[i++] = c;
        c = strtok(NULL, " ");
    }

	int SID = atoi(temp[1]);

    SREC* tmp = *front;
    SREC* prev = NULL;

    while (tmp->SID != SID && tmp->next != NULL) {
        prev = tmp;
        tmp = tmp->next;
    }

    if (tmp->SID == SID) {
        if (prev) {
            prev->next = tmp->next;
        }
        else {
            *front = tmp->next;
        }
        free(tmp);
        return 0;
    } else
		return 1;
	
	return 0;
}

char *SIDsort () {
    int tmp;
    SREC *temp;

    if (front == NULL) {
		return printRecords(); 
    } else {
        for (ptr = front;ptr != NULL;ptr = ptr->next)
        {
            for (temp = ptr->next;temp != NULL;temp = temp->next)
            {
                if (ptr->SID > temp->SID)
                {
                    tmp = ptr->SID;
                    ptr->SID = temp->SID;
                    temp->SID = tmp;
                }
            }
        }
        return printRecords();
    }
}

void error(char *msg)
{
    perror(msg);
    exit(1);
}

int insert (SREC **front, char *command) {
	char *temp[10];
	int i = 0;

	SREC *newnode = (SREC *)malloc(sizeof(SREC));
	char *c = strtok(command, " ");

// tokenize string
    while (c != NULL) {
        temp[i++] = c;
        c = strtok(NULL, " ");
    }
		
	if (newnode == NULL) { 
        return 1;									// Allocation Failed
    } else if (i != 6) {
		return 2;									// Not enough or too many arguments
	} else {	
		newnode->lname = strdup(temp[1]);
        newnode->fname = strdup(temp[2]);
        newnode->initial = strdup(temp[3]);
        newnode->SID = atoi(temp[4]);
        newnode->GPA = atoi(temp[5]);
        newnode->next = *front;
        *front = newnode; 
	}
	return 0;
}

void func(int sockfd) 
{ 
	char buff[MAX];
	int i = 0; 	
	char *bufferPtr = malloc(MAX);	

	// infinite loop for chat 
	for (;;) {

		bzero(buff, MAX);
		bzero(bufferPtr, MAX); 

		// read the message from client and copy it in buffer 
		read(sockfd, buff, sizeof(buff)); 
		// print buffer which contains the client contents 
		printf("From client: %s", buff); 
		
		if (strncmp("put", buff, 3) == 0) {	
			int j = insert(&front, buff);
			if (j == 1) {
				write(sockfd, "Allocation failed.", 18);
			} else if (j == 2) {
				write(sockfd, "Please input a new record in the form: (last name) (first name) (M.I.) (student ID) (GPA)", 89);
			} else 
				write(sockfd, "Record successfully added to the database.", 42);
		} else if (strncmp(buff, "get lname", 9) == 0) {
			bufferPtr = lnameSort(&front);	
			write(sockfd, bufferPtr, MAX); 	
		} else if (strncmp("get fname", buff, 9) == 0) {
			bufferPtr = fnameSort(&front);
			write(sockfd, bufferPtr, MAX); 	
		} else if (strncmp("get SID", buff, 7) == 0) {
			bufferPtr = SIDsort();
			write(sockfd, bufferPtr, MAX); 	
		} else if (strncmp("get GPA", buff, 7) == 0) {
			bufferPtr = GPAsort();
			write(sockfd, bufferPtr, MAX); 	
		} else if (strncmp("delete", buff, 6) == 0) {
			if (deleteRecord(&front, buff) == 0) {
				write(sockfd, "Record deleted successfully.", 28);
			} else
				write(sockfd, "That record could not be deleted because it does not exist.", 59);
		} else if (strncmp("exit", buff, 4) == 0) { 
			write(sockfd, buff, sizeof(buff)); 
			printf("Server Exit...\n"); 
			break; 
		} else {
			write(sockfd, "Sorry, that's not an option. Try again.", 39); 	
			continue;
		}	
	} 
} 

// Driver function 
int main() 
{ 
	int sockfd, connfd; 
	struct sockaddr_in servaddr, cli; 
	socklen_t len;

	// socket create and verification 
	sockfd = socket(AF_INET, SOCK_STREAM, 0); 
	if (sockfd == -1) { 
		printf("socket creation failed...\n"); 
		exit(0); 
	} 
	else
		printf("Socket successfully created..\n"); 
	bzero(&servaddr, sizeof(servaddr)); 

	// assign IP, PORT 
	servaddr.sin_family = AF_INET; 
	servaddr.sin_addr.s_addr = htonl(INADDR_ANY); 
	servaddr.sin_port = htons(PORT); 

	// Binding newly created socket to given IP and verification 
	if ((bind(sockfd, (SA*)&servaddr, sizeof(servaddr))) != 0) { 
		printf("socket bind failed...\n"); 
		exit(0); 
	} 
	else
		printf("Socket successfully binded..\n"); 

	// Now server is ready to listen and verification 
	if ((listen(sockfd, 5)) != 0) { 
		printf("Listen failed...\n"); 
		exit(0); 
	} 
	else
		printf("Server listening..\n"); 
	len = sizeof(cli); 

	// Accept the data packet from client and verification 
	connfd = accept(sockfd, (SA*)&cli, &len); 
	if (connfd < 0) { 
		printf("server acccept failed...\n"); 
		exit(0); 
	} 
	else
		printf("server acccept the client...\n"); 

	// Function for chatting between client and server 
	func(connfd); 

	// After chatting close the socket 
	close(sockfd); 
} 

