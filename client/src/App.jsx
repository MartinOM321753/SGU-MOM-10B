import React, { useState, useEffect } from "react";
import UserTable from "./components/UserTable";
import UserForm from "./components/UserForm";
import { getUsers } from "./modules/user/UserService";

function App() {
  const [showForm, setShowForm] = useState(false);
  const [selectedUser, setSelectedUser] = useState(null);
  const [users, setUsers] = useState([]);

  const fetchUsers = async () => {
    const res = await getUsers();
    setUsers(res.data);
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  const handleEdit = (user) => {
    setSelectedUser(user);
    setShowForm(true);
  };

  const handleCreate = () => {
    setSelectedUser(null);
    setShowForm(true);
  };

  const handleSave = () => {
    fetchUsers(); // 🔹 Recarga la tabla al guardar
  };

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-2xl font-bold mb-4">Gestión de Usuarios</h1>
      <button
        className="bg-green-500 text-white px-4 py-2 rounded"
        onClick={handleCreate}
      >
        Crear Usuario
      </button>

      <UserTable users={users} onEdit={handleEdit} />

      {showForm && (
        <UserForm
          selectedUser={selectedUser}
          onClose={() => setShowForm(false)}
          onSave={handleSave} // 🔹 Llama a la recarga
        />
      )}
    </div>
  );
}

export default App;
