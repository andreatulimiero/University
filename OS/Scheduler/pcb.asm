
pcb:     file format elf64-x86-64


Disassembly of section .init:

0000000000400528 <_init>:
  400528:	48 83 ec 08          	sub    $0x8,%rsp
  40052c:	48 8b 05 c5 0a 20 00 	mov    0x200ac5(%rip),%rax        # 600ff8 <_DYNAMIC+0x1e0>
  400533:	48 85 c0             	test   %rax,%rax
  400536:	74 05                	je     40053d <_init+0x15>
  400538:	e8 33 00 00 00       	callq  400570 <__gmon_start__@plt>
  40053d:	48 83 c4 08          	add    $0x8,%rsp
  400541:	c3                   	retq   

Disassembly of section .plt:

0000000000400550 <printf@plt-0x10>:
  400550:	ff 35 b2 0a 20 00    	pushq  0x200ab2(%rip)        # 601008 <_GLOBAL_OFFSET_TABLE_+0x8>
  400556:	ff 25 b4 0a 20 00    	jmpq   *0x200ab4(%rip)        # 601010 <_GLOBAL_OFFSET_TABLE_+0x10>
  40055c:	0f 1f 40 00          	nopl   0x0(%rax)

0000000000400560 <printf@plt>:
  400560:	ff 25 b2 0a 20 00    	jmpq   *0x200ab2(%rip)        # 601018 <_GLOBAL_OFFSET_TABLE_+0x18>
  400566:	68 00 00 00 00       	pushq  $0x0
  40056b:	e9 e0 ff ff ff       	jmpq   400550 <_init+0x28>

0000000000400570 <__gmon_start__@plt>:
  400570:	ff 25 aa 0a 20 00    	jmpq   *0x200aaa(%rip)        # 601020 <_GLOBAL_OFFSET_TABLE_+0x20>
  400576:	68 01 00 00 00       	pushq  $0x1
  40057b:	e9 d0 ff ff ff       	jmpq   400550 <_init+0x28>

0000000000400580 <__libc_start_main@plt>:
  400580:	ff 25 a2 0a 20 00    	jmpq   *0x200aa2(%rip)        # 601028 <_GLOBAL_OFFSET_TABLE_+0x28>
  400586:	68 02 00 00 00       	pushq  $0x2
  40058b:	e9 c0 ff ff ff       	jmpq   400550 <_init+0x28>

0000000000400590 <free@plt>:
  400590:	ff 25 9a 0a 20 00    	jmpq   *0x200a9a(%rip)        # 601030 <_GLOBAL_OFFSET_TABLE_+0x30>
  400596:	68 03 00 00 00       	pushq  $0x3
  40059b:	e9 b0 ff ff ff       	jmpq   400550 <_init+0x28>

00000000004005a0 <_Znwm@plt>:
  4005a0:	ff 25 92 0a 20 00    	jmpq   *0x200a92(%rip)        # 601038 <_GLOBAL_OFFSET_TABLE_+0x38>
  4005a6:	68 04 00 00 00       	pushq  $0x4
  4005ab:	e9 a0 ff ff ff       	jmpq   400550 <_init+0x28>

Disassembly of section .text:

00000000004005b0 <_start>:
  4005b0:	31 ed                	xor    %ebp,%ebp
  4005b2:	49 89 d1             	mov    %rdx,%r9
  4005b5:	5e                   	pop    %rsi
  4005b6:	48 89 e2             	mov    %rsp,%rdx
  4005b9:	48 83 e4 f0          	and    $0xfffffffffffffff0,%rsp
  4005bd:	50                   	push   %rax
  4005be:	54                   	push   %rsp
  4005bf:	49 c7 c0 80 07 40 00 	mov    $0x400780,%r8
  4005c6:	48 c7 c1 10 07 40 00 	mov    $0x400710,%rcx
  4005cd:	48 c7 c7 9d 06 40 00 	mov    $0x40069d,%rdi
  4005d4:	e8 a7 ff ff ff       	callq  400580 <__libc_start_main@plt>
  4005d9:	f4                   	hlt    
  4005da:	66 0f 1f 44 00 00    	nopw   0x0(%rax,%rax,1)

00000000004005e0 <deregister_tm_clones>:
  4005e0:	b8 57 10 60 00       	mov    $0x601057,%eax
  4005e5:	55                   	push   %rbp
  4005e6:	48 2d 50 10 60 00    	sub    $0x601050,%rax
  4005ec:	48 83 f8 0e          	cmp    $0xe,%rax
  4005f0:	48 89 e5             	mov    %rsp,%rbp
  4005f3:	77 02                	ja     4005f7 <deregister_tm_clones+0x17>
  4005f5:	5d                   	pop    %rbp
  4005f6:	c3                   	retq   
  4005f7:	b8 00 00 00 00       	mov    $0x0,%eax
  4005fc:	48 85 c0             	test   %rax,%rax
  4005ff:	74 f4                	je     4005f5 <deregister_tm_clones+0x15>
  400601:	5d                   	pop    %rbp
  400602:	bf 50 10 60 00       	mov    $0x601050,%edi
  400607:	ff e0                	jmpq   *%rax
  400609:	0f 1f 80 00 00 00 00 	nopl   0x0(%rax)

0000000000400610 <register_tm_clones>:
  400610:	b8 50 10 60 00       	mov    $0x601050,%eax
  400615:	55                   	push   %rbp
  400616:	48 2d 50 10 60 00    	sub    $0x601050,%rax
  40061c:	48 c1 f8 03          	sar    $0x3,%rax
  400620:	48 89 e5             	mov    %rsp,%rbp
  400623:	48 89 c2             	mov    %rax,%rdx
  400626:	48 c1 ea 3f          	shr    $0x3f,%rdx
  40062a:	48 01 d0             	add    %rdx,%rax
  40062d:	48 d1 f8             	sar    %rax
  400630:	75 02                	jne    400634 <register_tm_clones+0x24>
  400632:	5d                   	pop    %rbp
  400633:	c3                   	retq   
  400634:	ba 00 00 00 00       	mov    $0x0,%edx
  400639:	48 85 d2             	test   %rdx,%rdx
  40063c:	74 f4                	je     400632 <register_tm_clones+0x22>
  40063e:	5d                   	pop    %rbp
  40063f:	48 89 c6             	mov    %rax,%rsi
  400642:	bf 50 10 60 00       	mov    $0x601050,%edi
  400647:	ff e2                	jmpq   *%rdx
  400649:	0f 1f 80 00 00 00 00 	nopl   0x0(%rax)

0000000000400650 <__do_global_dtors_aux>:
  400650:	80 3d f9 09 20 00 00 	cmpb   $0x0,0x2009f9(%rip)        # 601050 <__TMC_END__>
  400657:	75 11                	jne    40066a <__do_global_dtors_aux+0x1a>
  400659:	55                   	push   %rbp
  40065a:	48 89 e5             	mov    %rsp,%rbp
  40065d:	e8 7e ff ff ff       	callq  4005e0 <deregister_tm_clones>
  400662:	5d                   	pop    %rbp
  400663:	c6 05 e6 09 20 00 01 	movb   $0x1,0x2009e6(%rip)        # 601050 <__TMC_END__>
  40066a:	f3 c3                	repz retq 
  40066c:	0f 1f 40 00          	nopl   0x0(%rax)

0000000000400670 <frame_dummy>:
  400670:	48 83 3d 98 07 20 00 	cmpq   $0x0,0x200798(%rip)        # 600e10 <__JCR_END__>
  400677:	00 
  400678:	74 1e                	je     400698 <frame_dummy+0x28>
  40067a:	b8 00 00 00 00       	mov    $0x0,%eax
  40067f:	48 85 c0             	test   %rax,%rax
  400682:	74 14                	je     400698 <frame_dummy+0x28>
  400684:	55                   	push   %rbp
  400685:	bf 10 0e 60 00       	mov    $0x600e10,%edi
  40068a:	48 89 e5             	mov    %rsp,%rbp
  40068d:	ff d0                	callq  *%rax
  40068f:	5d                   	pop    %rbp
  400690:	e9 7b ff ff ff       	jmpq   400610 <register_tm_clones>
  400695:	0f 1f 00             	nopl   (%rax)
  400698:	e9 73 ff ff ff       	jmpq   400610 <register_tm_clones>

000000000040069d <main>:
  40069d:	55                   	push   %rbp
  40069e:	48 89 e5             	mov    %rsp,%rbp
  4006a1:	48 83 ec 20          	sub    $0x20,%rsp
  4006a5:	89 7d ec             	mov    %edi,-0x14(%rbp)
  4006a8:	48 89 75 e0          	mov    %rsi,-0x20(%rbp)
  4006ac:	48 c7 45 f0 94 07 40 	movq   $0x400794,-0x10(%rbp)
  4006b3:	00 
  4006b4:	bf 18 00 00 00       	mov    $0x18,%edi
  4006b9:	e8 e2 fe ff ff       	callq  4005a0 <_Znwm@plt>
  4006be:	c7 00 00 00 00 00    	movl   $0x0,(%rax)
  4006c4:	48 c7 40 08 00 00 00 	movq   $0x0,0x8(%rax)
  4006cb:	00 
  4006cc:	c7 40 10 00 00 00 00 	movl   $0x0,0x10(%rax)
  4006d3:	48 89 45 f8          	mov    %rax,-0x8(%rbp)
  4006d7:	48 8b 45 f8          	mov    -0x8(%rbp),%rax
  4006db:	c7 00 64 00 00 00    	movl   $0x64,(%rax)
  4006e1:	48 8b 45 f8          	mov    -0x8(%rbp),%rax
  4006e5:	8b 00                	mov    (%rax),%eax
  4006e7:	89 c6                	mov    %eax,%esi
  4006e9:	bf 9e 07 40 00       	mov    $0x40079e,%edi
  4006ee:	b8 00 00 00 00       	mov    $0x0,%eax
  4006f3:	e8 68 fe ff ff       	callq  400560 <printf@plt>
  4006f8:	48 8b 45 f8          	mov    -0x8(%rbp),%rax
  4006fc:	48 89 c7             	mov    %rax,%rdi
  4006ff:	e8 8c fe ff ff       	callq  400590 <free@plt>
  400704:	b8 00 00 00 00       	mov    $0x0,%eax
  400709:	c9                   	leaveq 
  40070a:	c3                   	retq   
  40070b:	0f 1f 44 00 00       	nopl   0x0(%rax,%rax,1)

0000000000400710 <__libc_csu_init>:
  400710:	41 57                	push   %r15
  400712:	41 89 ff             	mov    %edi,%r15d
  400715:	41 56                	push   %r14
  400717:	49 89 f6             	mov    %rsi,%r14
  40071a:	41 55                	push   %r13
  40071c:	49 89 d5             	mov    %rdx,%r13
  40071f:	41 54                	push   %r12
  400721:	4c 8d 25 d8 06 20 00 	lea    0x2006d8(%rip),%r12        # 600e00 <__frame_dummy_init_array_entry>
  400728:	55                   	push   %rbp
  400729:	48 8d 2d d8 06 20 00 	lea    0x2006d8(%rip),%rbp        # 600e08 <__init_array_end>
  400730:	53                   	push   %rbx
  400731:	4c 29 e5             	sub    %r12,%rbp
  400734:	31 db                	xor    %ebx,%ebx
  400736:	48 c1 fd 03          	sar    $0x3,%rbp
  40073a:	48 83 ec 08          	sub    $0x8,%rsp
  40073e:	e8 e5 fd ff ff       	callq  400528 <_init>
  400743:	48 85 ed             	test   %rbp,%rbp
  400746:	74 1e                	je     400766 <__libc_csu_init+0x56>
  400748:	0f 1f 84 00 00 00 00 	nopl   0x0(%rax,%rax,1)
  40074f:	00 
  400750:	4c 89 ea             	mov    %r13,%rdx
  400753:	4c 89 f6             	mov    %r14,%rsi
  400756:	44 89 ff             	mov    %r15d,%edi
  400759:	41 ff 14 dc          	callq  *(%r12,%rbx,8)
  40075d:	48 83 c3 01          	add    $0x1,%rbx
  400761:	48 39 eb             	cmp    %rbp,%rbx
  400764:	75 ea                	jne    400750 <__libc_csu_init+0x40>
  400766:	48 83 c4 08          	add    $0x8,%rsp
  40076a:	5b                   	pop    %rbx
  40076b:	5d                   	pop    %rbp
  40076c:	41 5c                	pop    %r12
  40076e:	41 5d                	pop    %r13
  400770:	41 5e                	pop    %r14
  400772:	41 5f                	pop    %r15
  400774:	c3                   	retq   
  400775:	66 66 2e 0f 1f 84 00 	data32 nopw %cs:0x0(%rax,%rax,1)
  40077c:	00 00 00 00 

0000000000400780 <__libc_csu_fini>:
  400780:	f3 c3                	repz retq 

Disassembly of section .fini:

0000000000400784 <_fini>:
  400784:	48 83 ec 08          	sub    $0x8,%rsp
  400788:	48 83 c4 08          	add    $0x8,%rsp
  40078c:	c3                   	retq   
