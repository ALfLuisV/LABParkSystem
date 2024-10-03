"use client";
import Axios from "axios";
import { useEffect, useState } from "react";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Button } from "@/components/ui/button";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { Home, User, Building2, Pencil, X } from "lucide-react";

export default function CarRentalSystem() {
  const [view, setView] = useState("customer");
  const [rentals, setRentals] = useState<any[]>([]);
  const [editingRental, setEditingRental] = useState(null);
  const [carRows, setCarRows] = useState<any[]>([]); // Tipo ajustado para array

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const formData = new FormData(event.target as HTMLFormElement);
    const data = Object.fromEntries(formData.entries());
    const [carModel, diary] = (data.car as string).split("/");
    const wats = data.dates;
    console.log(carModel);
    console.log(diary);
    console.log(wats);
  };

  const handleCancel = (id: number) => {
    setRentals(rentals.filter((rental) => rental.id !== id));
  };

  const handleModify = (rental: any) => {
    setEditingRental(rental);
  };

  const handleUpdate = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const updatedRental = {
      ...(editingRental as any),
    };
    setRentals(
      rentals.map((rental) =>
        rental.id === updatedRental.id ? updatedRental : rental
      )
    );
    setEditingRental(null);
  };

  const handleEvaluate = (id: number, status: string) => {
    setRentals(
      rentals.map((rental) =>
        rental.idaluguel === id ? { ...rental, status } : rental
      )
    );
  };

  // Função para buscar os veículos registrados na base de dados
  async function fetchCars() {
    try {
      const response = await Axios.get("http://localhost:3002/veiculos");
      setCarRows(setCarList(response.data));
    } catch (e) {
      console.log(e);
    }
  }

  // Insere os dados dos veículos em uma lista de um select no frontend
  function setCarList(array: any[]) {
    const carRows = array.map((elements) => (
      <SelectItem
        value={elements.idVeic + "/" + elements.diaria}
        key={elements.idVeic} // Usando o idVeic como chave
      >
        {elements.modelo + " - " + elements.diaria + "/dia"}
      </SelectItem>
    ));
    return carRows;
  }

  useEffect(() => {
    fetchCars();
  }, []);


  useEffect(() => {
    Axios.get("http://localhost:3002/alugueis")
      .then((response) => {   
        setRentals(response.data);
        console.log(response.data); 
      })
      .catch((error) => {
        console.error("Ocorreu um erro ao buscar os alugueis:", error);
      });
  }, []);


  return (
    <div className="flex flex-col min-h-screen">
      <header className="bg-primary text-primary-foreground shadow-md">
        <div className="container mx-auto px-4">
          <div className="flex items-center justify-between h-16">
            <div className="flex items-center">
              <Home className="h-6 w-6 mr-2" />
              <span className="font-bold text-lg">Car Rental System</span>
            </div>
            <nav>
              <ul className="flex space-x-4">
                <li>
                  <Button
                    variant={view === "customer" ? "secondary" : "ghost"}
                    onClick={() => setView("customer")}
                  >
                    <User className="h-4 w-4 mr-2" />
                    Customer
                  </Button>
                </li>
                <li>
                  <Button
                    variant={view === "agent" ? "secondary" : "ghost"}
                    onClick={() => setView("agent")}
                  >
                    <Building2 className="h-4 w-4 mr-2" />
                    Agent
                  </Button>
                </li>
              </ul>
            </nav>
          </div>
        </div>
      </header>

      <main className="flex-grow container mx-auto p-4">
        {view === "customer" ? (
          <div className="space-y-4">
            <Card>
              <CardHeader>
                <CardTitle>{editingRental ? "Modify Rental Request" : "New Rental Request"}</CardTitle>
                <CardDescription>{editingRental ? "Update your rental request here." : "Enter your rental request here."}</CardDescription>
              </CardHeader>
              <CardContent>
                <form onSubmit={editingRental ? handleUpdate : handleSubmit}>
                  <div className="grid w-full items-center gap-4">
                    <div className="flex flex-col space-y-1.5">
                      <Label htmlFor="car">Car Type</Label>
                      <Select name="car" defaultValue={editingRental || ''}>
                        <SelectTrigger id="car">
                          <SelectValue placeholder="Select car type"/>
                        </SelectTrigger>
                        <SelectContent position="popper">
                          {carRows}
                        </SelectContent>
                      </Select>
                    </div>
                    <div className="flex flex-col space-y-1.5">
                      <Label htmlFor="dates">Rental Dates</Label>
                      <Input id="dates" name="dates" type="date" defaultValue={editingRental || ''} />
                    </div>
                  </div>
                  <div className="flex justify-between mt-4">
                    <Button type="button" variant="outline" onClick={() => setEditingRental(null)}>Cancel</Button>
                    <Button type="submit">{editingRental ? "Update Request" : "Submit Request"}</Button>
                  </div>
                </form>
              </CardContent>
            </Card>
            <Card>
              <CardHeader>
                <CardTitle>Your Rental Requests</CardTitle>
              </CardHeader>
              <CardContent>
                <div className="overflow-x-auto">
                  <Table>
                    <TableHeader>
                      <TableRow>
                        <TableHead className="w-[15%]">ID</TableHead>
                        <TableHead className="w-[25%]">Carro</TableHead>
                        <TableHead className="w-[25%]">Data</TableHead>
                        <TableHead className="w-[25%]">Status</TableHead>
                        <TableHead className="w-[35%]">Ações</TableHead>
                      </TableRow>
                    </TableHeader>
                    <TableBody>
                      {rentals.map((rental) => (
                        <TableRow key={rental.idaluguel}>
                          <TableCell className="w-[10%]">{rental.idaluguel}</TableCell>
                          <TableCell className="w-[20%]">{rental.idveiculo.modelo}</TableCell>
                          <TableCell className="w-[20%]">{rental.data}</TableCell>
                          <TableCell className="w-[20%]">{rental.status}</TableCell>
                          <TableCell className="w-[30%]">
                            <div className="flex space-x-2">
                              <Button variant="outline" size="sm" onClick={() => handleModify(rental)}>
                                <Pencil className="h-4 w-4 mr-1" />
                                Modify
                              </Button>
                              <Button variant="destructive" size="sm" onClick={() => handleCancel(rental.id)}>
                                <X className="h-4 w-4 mr-1" />
                                Cancel
                              </Button>
                            </div>
                          </TableCell>
                        </TableRow>
                      ))}
                    </TableBody>
                  </Table>
                </div>
              </CardContent>
            </Card>
          </div>
        ) : (
          <Card>
            <CardHeader>
              <CardTitle>Evaluate Rental Requests</CardTitle>
              <CardDescription>Review and update rental request statuses.</CardDescription>
            </CardHeader>
            <CardContent>
              <div className="overflow-x-auto">
                <Table>
                  <TableHeader>
                    <TableRow>
                      <TableHead className="w-[10%]">ID</TableHead>
                      <TableHead className="w-[20%]">Cliente</TableHead>
                      <TableHead className="w-[20%]">Carro</TableHead>
                      <TableHead className="w-[20%]">Data</TableHead>
                      <TableHead className="w-[15%]">Status</TableHead>
                      <TableHead className="w-[15%]">Action</TableHead>
                    </TableRow>
                  </TableHeader>
                  <TableBody>
                    {rentals.map((rental) => (
                      <TableRow key={rental.idaluguel}>
                        <TableCell className="w-[10%]">{rental.idaluguel}</TableCell>
                        <TableCell className="w-[20%]">{rental.idcliente.nome}</TableCell>
                        <TableCell className="w-[20%]">{rental.idveiculo.modelo}</TableCell>
                        <TableCell className="w-[20%]">{rental.data}</TableCell>
                        <TableCell className="w-[15%]">{rental.status}</TableCell>
                        <TableCell className="w-[15%]">
                          <Select onValueChange={(value) => handleEvaluate(rental.id, value)}>
                            <SelectTrigger>
                              <SelectValue placeholder="Update status" />
                            </SelectTrigger>
                            <SelectContent>
                              <SelectItem value="aprovado">Aprovado</SelectItem>
                              <SelectItem value="rejeitado">Rejeitado</SelectItem>
                              <SelectItem value="pendente">Pendente</SelectItem>
                            </SelectContent>
                          </Select>
                        </TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </div>
            </CardContent>
          </Card>
        )}
      </main>
    </div>
  )
}
