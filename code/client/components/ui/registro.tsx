"use client"; // Isso marca o componente como Client Component

import { useState } from 'react';
import { useRouter } from 'next/navigation'; // Usado para navegação no Next.js 13+
import { Input } from '@/components/ui/input';
import { Button } from '@/components/ui/button';
import { Label } from '@/components/ui/label';
import {
  Select,
  SelectTrigger,
  SelectValue,
  SelectContent,
  SelectItem
} from '@/components/ui/select'; // Importa o Select do Shadcn

const Registro = () => {
  const [formData, setFormData] = useState({
    username: '',
    senha: '',
    tipoUsuario: 'CLIENTE', // Valor inicial padrão
  });

  const router = useRouter();

  // Função para lidar com alterações no formulário
  const handleChange = (key: string, value: string) => {
    setFormData((prevData) => ({ ...prevData, [key]: value }));
  };

  // Função para lidar com o envio do formulário
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const res = await fetch('http://localhost:3002/usuarios/registrar', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(formData),
      });
      if (res.ok) {
        const data = await res.json();
        alert('Usuário registrado com sucesso!');
        router.push('/login'); // Redireciona para a página de login após o registro
      } else {
        throw new Error('Erro ao registrar o usuário.');
      }
    } catch (error) {
      alert(error.message);
    }
  };

  return (
    <div className="flex justify-center items-center h-screen">
      <form onSubmit={handleSubmit} className="bg-white p-6 rounded shadow-md">
        <h2 className="text-lg font-bold mb-4">Registrar</h2>
        <div className="mb-4">
          <Label htmlFor="username">Nome de Usuário</Label>
          <Input
            type="text"
            name="username"
            value={formData.username}
            onChange={(e) => handleChange("username", e.target.value)}
            required
          />
        </div>
        <div className="mb-4">
          <Label htmlFor="senha">Senha</Label>
          <Input
            type="password"
            name="senha"
            value={formData.senha}
            onChange={(e) => handleChange("senha", e.target.value)}
            required
          />
        </div>
        <div className="mb-4">
          <Label htmlFor="tipoUsuario">Tipo de Usuário</Label>
          <Select onValueChange={(value) => handleChange("tipoUsuario", value)}>
            <SelectTrigger>
              <SelectValue placeholder="Selecione o tipo de usuário" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="CLIENTE">Cliente</SelectItem>
              <SelectItem value="AGENTE">Agente</SelectItem>
            </SelectContent>
          </Select>
        </div>
        <Button type="submit">Registrar</Button>
      </form>
    </div>
  );
};

export default Registro;
