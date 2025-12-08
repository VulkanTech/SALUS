import { useEffect, useState } from "react";
import api from "../api/api";

export default function Pacientes() {
    const [pacientes, setPacientes] = useState([]);

    useEffect(() => {
        api.get("/pacientes").then(res => setPacientes(res.data));
    }, []);

    return (
        <>
            <h2>Pacientes</h2>

            <ul>
                {pacientes.map(p => (
                    <li key={p.cpf}>
                        {p.nome} – {p.doenca}
                    </li>
                ))}
            </ul>
        </>
    );
}
