import { AuthenticationService } from '@nuvem/angular-base';
import { Observable } from 'rxjs';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Usuario } from 'src/app/dominios/usuario';
import { Component, Input, OnInit } from '@angular/core';
import { UsuarioService } from 'src/app/modulos/usuario/services/usuario.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @Input() usuario = new Usuario();

  loginUsuario: FormGroup;
    loading = false;
    submitted = false;
    returnUrl: string;
    error = '';

  constructor(
    private fb: FormBuilder,
    private usuarioService: UsuarioService,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService<Usuario>
  ) { 
    if (this.authenticationService.isAuthenticated()) { 
      this.router.navigate(['/']);
  }
  }

  ngOnInit(): void {
    this.loginUsuario = this.fb.group({
        email: ['', Validators.required],
        chave: ['', Validators.required]
    });

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
}

get f() { return this.loginUsuario.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.loginUsuario.invalid) {
            return;
        }

        this.loading = true;
        this.authenticationService.login()
    
}

  buscarUsuarioPorEmail(email: string) {
    this.usuarioService.buscarUsuarioPorEmail(email)
      .subscribe(usuarios => {
        this.usuario = usuarios;
      });
  }

  entrar(email: string, chave: string) {
    this.usuarioService.loginSucesso()
    .subscribe(usuarios => {
      alert('usuario logou');
    });
  }

}
