import { Component, OnInit, Input } from '@angular/core';
import { Usuario } from '../models/usuario';
import { UserService } from '../service/user.service';
import { ConfirmationService } from 'primeng/api';
import { MessageService } from 'primeng/api';


@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

    usuarioDialogo: boolean;

    usuarios: Usuario[];

    usuario: Usuario;

    usuariosSelecionados: Usuario[];

    submitted: boolean;

    constructor(private usuarioService: UserService, 
        private messageService: MessageService, 
        private confirmationService: ConfirmationService) { }


    ngOnInit() {
        this.listarUsuarios();
        console.log("total de usuarios")
        console.log(this.usuarios);
    }

    
    listarUsuarios(): void {
        this.usuarioService.getUsuarios()
            .subscribe(
            data => {
                this.usuarios = data;
                console.log(data);
            },
            error => {
                console.log(error);
            });
        }


    abrirNovo(){
        
        this.usuario = {};
        this.submitted = false;
        this.usuarioDialogo = true;
    }

    deletarUsuariosSelecionados(){

        this.confirmationService.confirm({
            message: 'Tem certeza que vai  deletar os usuarios Selecioandos?',
            header: 'Confirmar',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.usuarios = this.usuarios.filter(val => !this.usuariosSelecionados.includes(val));
                this.usuariosSelecionados = null;
                this.messageService.add({severity:'success', summary: 'Successful', detail: 'usuarios deletados', life: 3000});
            }
        });
    }

    salvaUsuario(){

        this.submitted = true;

        if (this.usuario.nome.trim()) {
            if (this.usuario.id) {
                this.usuarios[this.findIndexById(this.usuario.id)] = this.usuario;                
                this.messageService.add({severity:'success', summary: 'Successful', detail: 'usuario atualizado', life: 3000});
            }
            else {
                this.usuarioService.addUsuario(this.usuario)
                .subscribe(
                    response => {
                    console.log(response);
                    this.submitted = true;
                    },
                    error => {
                    console.log(error);
                    });
                this.usuario.id = this.createId();
                this.usuarios.push(this.usuario);
                this.messageService.add({severity:'success', summary: 'Successful', detail: 'Equpamento salvo', life: 3000});
            }

            this.usuarios = [...this.usuarios];
            this.usuarioDialogo = false;
            this.usuario = {};
        }
    }


    deletaUsuario(usuario: Usuario){

        this.confirmationService.confirm({
            message: 'Tem certeza que quer deletar o usuario ' + usuario.nome + '?',
            header: 'Confirmar',
            icon: 'pi pi-exclamation-triangle',
            accept: () => {
                this.usuarios = this.usuarios.filter(val => val.id !== usuario.id);
                this.usuario = {};
                this.messageService.add({severity:'success', summary: 'Successful', detail: 'usuario deletado', life: 3000});
            }
        });
    }

    editaUsuario(usuario: Usuario) {

        this.usuario = {...usuario};
        this.usuarioDialogo = true;
    }

    escondeDialogo(){

        this.usuarioDialogo = false;
        this.submitted = false;

    }


    findIndexById(id: string): number {
        let index = -1;
        for (let i = 0; i < this.usuarios.length; i++) {
            if (this.usuarios[i].id === id) {
                index = i;
                break;
            }
        }

        return index;
    }

    createId(): string {
        let id = '';
        var chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
        for ( var i = 0; i < 5; i++ ) {
            id += chars.charAt(Math.floor(Math.random() * chars.length));
        }
        return id;
    }


}
