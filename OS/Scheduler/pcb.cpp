#include <stdio.h>
#include <stdlib.h>


enum State { Running, Ready, Zombie, Created, IOWait };

struct MemInfo {

};

struct CpuState {

};

struct PCB {
  int pid;
  void *stack_pointer;
  State scheduling_state;
  CpuState cpu_state;
  MemInfo mem_info;
};

#define GIOVANNI 1

int main(int argc, char** argv) {
  PCB* pcb = new PCB();
  pcb->pid = 100;
  int a = GIOVANNI;
  printf("Hello PCB #%d \n", pcb->pid);
  free(pcb);
}