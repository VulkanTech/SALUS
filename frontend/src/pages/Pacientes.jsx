import { useEffect, useState } from "react";
import api from "../api/api";

console.log("Pacientes.jsx carregou");


export default function Pacientes() {
    const [pacientes, setPacientes] = useState([]);

    const [cpf, setCpf] = useState("");
    const [nome, setNome] = useState("");
    const [email, setEmail] = useState("");
    const [telefone, setTelefone] = useState("");
    const [idade, setIdade] = useState("");
    const [doenca, setDoenca] = useState("");

    const [loading, setLoading] = useState(false);
    const [erro, setErro] = useState(null);

    useEffect(() => {
        carregarPacientes();
    }, []);

    function carregarPacientes() {
        setLoading(true);

        api.get("/pacientes")
            .then(res => {
                setPacientes(res.data);
                setErro(null);
            })
            .catch(() => setErro("Erro ao carregar pacientes"))
            .finally(() => setLoading(false));
    }

    function salvar(e) {
        e.preventDefault();

        if (!cpf || !nome || !email || !telefone || !idade) {
            setErro("Preencha todos os campos obrigatórios");
            return;
        }

        setLoading(true);

        api.post("/pacientes", {
            cpf,
            nome,
            email,
            telefone,
            idade: Number(idade),
            doenca,
        })
            .then(() => {
                setCpf("");
                setNome("");
                setEmail("");
                setTelefone("");
                setIdade("");
                setDoenca("");
                carregarPacientes();
            })
            .catch(err => {
                setErro(err.response?.data?.message || "Erro ao cadastrar paciente");
            })
            .finally(() => setLoading(false));
    }

    return (
        <div>
            <h2>Pacientes</h2>

            {erro && <p style={{ color: "red" }}>{erro}</p>}
            {loading && <p>Carregando...</p>}

            <form onSubmit={salvar} style={{ marginBottom: 20 }}>
                <input
                    placeholder="CPF"
                    value={cpf}
                    onChange={e => setCpf(e.target.value)}
                />

                <input
                    placeholder="Nome"
                    value={nome}
                    onChange={e => setNome(e.target.value)}
                />

                <input
                    placeholder="Email"
                    value={email}
                    onChange={e => setEmail(e.target.value)}
                />

                <input
                    placeholder="Telefone"
                    value={telefone}
                    onChange={e => setTelefone(e.target.value)}
                />

                <input
                    type="number"
                    placeholder="Idade"
                    value={idade}
                    onChange={e => setIdade(e.target.value)}
                />

                <input
                    placeholder="Doença (opcional)"
                    value={doenca}
                    onChange={e => setDoenca(e.target.value)}
                />

                <button type="submit">Cadastrar</button>
            </form>

            <ul>
                {pacientes.map(p => (
                    <li key={p.cpf} style={{ marginBottom: 10 }}>
                        <strong>{p.nome}</strong>
                        <br />
                        CPF: {p.cpf}
                        <br />
                        Email: {p.email}
                        <br />
                        Telefone: {p.telefone}
                        <br />
                        Idade: {p.idade}
                        {p.doenca && <><br />Doença: {p.doenca}</>}
                    </li>
                ))}
            </ul>
        </div>
    );

}
