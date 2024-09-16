"use client";
import { useState } from "react";
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
  const [rentals, setRentals] = useState([
    {
      id: 1,
      customer: "John Doe",
      car: "Sedan",
      status: "Pending",
      dates: "2023-06-01",
    },
    {
      id: 2,
      customer: "Jane Smith",
      car: "SUV",
      status: "Approved",
      dates: "2023-06-15",
    },
  ]);

  const [editingRental, setEditingRental] = useState(null);

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    // Handle form submission logic here
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
        rental.id === id ? { ...rental, status } : rental
      )
    );
  };

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
          <>
            <Card className="mb-4">
              <CardHeader>
                <CardTitle>
                  {editingRental
                    ? "Modify Rental Request"
                    : "New Rental Request"}
                </CardTitle>
                <CardDescription>
                  {editingRental
                    ? "Update your rental request here."
                    : "Enter your rental request here."}
                </CardDescription>
              </CardHeader>
              <CardContent>
                <form onSubmit={editingRental ? handleUpdate : handleSubmit}>
                  <div className="grid w-full items-center gap-4">
                    <div className="flex flex-col space-y-1.5">
                      <Label htmlFor="name">Name</Label>
                      <Input
                        id="name"
                        placeholder="Enter your name"
                        defaultValue={editingRental || ("" as any)}
                        readOnly={!!editingRental}
                      />
                    </div>
                    <div className="flex flex-col space-y-1.5">
                      <Label htmlFor="car">Car Type</Label>
                      <Select
                        name="car"
                        defaultValue={editingRental || ("" as any)}
                      >
                        <SelectTrigger id="car">
                          <SelectValue placeholder="Select car type" />
                        </SelectTrigger>
                        <SelectContent position="popper">
                          <SelectItem value="Sedan">Sedan</SelectItem>
                          <SelectItem value="SUV">SUV</SelectItem>
                          <SelectItem value="Sports Car">Sports Car</SelectItem>
                        </SelectContent>
                      </Select>
                    </div>
                    <div className="flex flex-col space-y-1.5">
                      <Label htmlFor="dates">Rental Dates</Label>
                      <Input
                        id="dates"
                        name="dates"
                        type="date"
                        defaultValue={editingRental || ("" as any)}
                      />
                    </div>
                  </div>
                  <div className="flex justify-between mt-4">
                    <Button
                      type="button"
                      variant="outline"
                      onClick={() => setEditingRental(null)}
                    >
                      Cancel
                    </Button>
                    <Button type="submit">
                      {editingRental ? "Update Request" : "Submit Request"}
                    </Button>
                  </div>
                </form>
              </CardContent>
            </Card>
            <Card>
              <CardHeader>
                <CardTitle>Your Rental Requests</CardTitle>
              </CardHeader>
              <CardContent>
                <Table>
                  <TableHeader>
                    <TableRow>
                      <TableHead>ID</TableHead>
                      <TableHead>Car Type</TableHead>
                      <TableHead>Dates</TableHead>
                      <TableHead>Status</TableHead>
                      <TableHead>Actions</TableHead>
                    </TableRow>
                  </TableHeader>
                  <TableBody>
                    {rentals.map((rental) => (
                      <TableRow key={rental.id}>
                        <TableCell>{rental.id}</TableCell>
                        <TableCell>{rental.car}</TableCell>
                        <TableCell>{rental.dates}</TableCell>
                        <TableCell>{rental.status}</TableCell>
                        <TableCell>
                          <div className="flex space-x-2">
                            <Button
                              variant="outline"
                              size="sm"
                              onClick={() => handleModify(rental)}
                            >
                              <Pencil className="h-4 w-4 mr-1" />
                              Modify
                            </Button>
                            <Button
                              variant="destructive"
                              size="sm"
                              onClick={() => handleCancel(rental.id)}
                            >
                              <X className="h-4 w-4 mr-1" />
                              Cancel
                            </Button>
                          </div>
                        </TableCell>
                      </TableRow>
                    ))}
                  </TableBody>
                </Table>
              </CardContent>
            </Card>
          </>
        ) : (
          <Card>
            <CardHeader>
              <CardTitle>Evaluate Rental Requests</CardTitle>
              <CardDescription>
                Review and update rental request statuses.
              </CardDescription>
            </CardHeader>
            <CardContent>
              <Table>
                <TableHeader>
                  <TableRow>
                    <TableHead>ID</TableHead>
                    <TableHead>Customer</TableHead>
                    <TableHead>Car Type</TableHead>
                    <TableHead>Dates</TableHead>
                    <TableHead>Status</TableHead>
                    <TableHead>Action</TableHead>
                  </TableRow>
                </TableHeader>
                <TableBody>
                  {rentals.map((rental) => (
                    <TableRow key={rental.id}>
                      <TableCell>{rental.id}</TableCell>
                      <TableCell>{rental.customer}</TableCell>
                      <TableCell>{rental.car}</TableCell>
                      <TableCell>{rental.dates}</TableCell>
                      <TableCell>{rental.status}</TableCell>
                      <TableCell>
                        <Select
                          onValueChange={(value) =>
                            handleEvaluate(rental.id, value)
                          }
                        >
                          <SelectTrigger>
                            <SelectValue placeholder="Update status" />
                          </SelectTrigger>
                          <SelectContent>
                            <SelectItem value="Approved">Approve</SelectItem>
                            <SelectItem value="Rejected">Reject</SelectItem>
                            <SelectItem value="Pending">Pending</SelectItem>
                          </SelectContent>
                        </Select>
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </CardContent>
          </Card>
        )}
      </main>
    </div>
  );
}
